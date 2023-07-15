package com.example.lerword_mvp.Presenters

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.lerword.Model.Database.WordsRepository
import com.example.lerword_mvp.Model.Word
import com.example.lerword_mvp.views.MainView
import com.yuyakaido.android.cardstackview.Direction
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/*
 * Created by Skyain1 on 15.07.2023.
 */

@InjectViewState
class MainPresenter (private val repository: WordsRepository): MvpPresenter<MainView>() {
    private var words = ArrayList<Word>()
    private var fullwords = ArrayList<Word>()
    private var curword =0
    private var knowWords =0
    private var swipe = 0

    fun cardSwipe(direction: Direction?){
        if (direction == Direction.Right) {
            if (swipe == 0) {
                words[10] = words[curword]
                swipe++
            }
        } else if (direction == Direction.Left) {
            updateKnow(words[curword])
        }
        iteration()
    }

    fun setTenWords() {
        curword = 0
        swipe = 0
        words.clear()
        GlobalScope.launch(Dispatchers.Main) {
            withContext(Dispatchers.IO) {
                fullwords = repository.getWords()
            }
            repeat(11) {
                val word = fullwords.find { !it.know && !words.contains(it) }
                if (word != null) {
                    words.add(word)
                }
            }
            viewState.getData(words)
        }
    }

    private fun updateKnow(word: Word) {
        GlobalScope.launch(Dispatchers.Main) {
            withContext(Dispatchers.IO) {
                repository.setKnow(word)
            }
            knowWords++
            if(knowWords==10){
                viewState.setprogress(knowWords)
                viewState.setRestart()
                knowWords=0
            }else{
                viewState.setprogress(knowWords)
            }
        }
    }

    private fun updateCount(word: Word) {
        GlobalScope.launch(Dispatchers.Main) {
            withContext(Dispatchers.IO) {
                repository.updateField(word)
            }
        }
    }

    private fun iteration() {
        updateCount(words[curword])
        curword++
        if (curword == 10) {
            setTenWords()
        }
    }
}