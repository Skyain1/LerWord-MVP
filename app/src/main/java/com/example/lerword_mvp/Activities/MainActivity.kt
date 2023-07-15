package com.example.lerword_mvp.Activities

import android.os.Bundle
import android.view.View
import android.view.animation.AccelerateInterpolator
import android.widget.ImageView
import android.widget.Toast
import com.arellomobile.mvp.MvpActivity
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.lerword.Model.Database.WordsRepository
import com.example.lerword_mvp.Adapter.ButtonClickListener
import com.example.lerword_mvp.Adapter.CardStackAdapter
import com.example.lerword_mvp.Model.Word
import com.example.lerword_mvp.Presenters.LoginPresenter
import com.example.lerword_mvp.Presenters.MainPresenter
import com.example.lerword_mvp.Presenters.SignUpPresenter
import com.example.lerword_mvp.R
import com.example.lerword_mvp.databinding.ActivityMainBinding
import com.example.lerword_mvp.views.MainView
import com.yuyakaido.android.cardstackview.CardStackLayoutManager
import com.yuyakaido.android.cardstackview.CardStackListener
import com.yuyakaido.android.cardstackview.CardStackView
import com.yuyakaido.android.cardstackview.Direction
import com.yuyakaido.android.cardstackview.Duration
import com.yuyakaido.android.cardstackview.SwipeAnimationSetting

/*
 * Created by Skyain1 on 15.07.2023.
 */

class MainActivity : MvpActivity(), CardStackListener,MainView, ButtonClickListener {

    @InjectPresenter
    lateinit var mainPresenter: MainPresenter

    private lateinit var binding: ActivityMainBinding
    private var repository: WordsRepository = WordsRepository()
    private lateinit var cardStackView: CardStackView
    private lateinit var CSLmanager: CardStackLayoutManager

    @ProvidePresenter
    fun provideMainPresenter(): MainPresenter {
        return MainPresenter(repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        cardStackView = findViewById(R.id.card_stack_view)
        CSLmanager = CardStackLayoutManager(this@MainActivity, this)
        CSLmanager.setCanScrollHorizontal(true)
        CSLmanager.setCanScrollVertical(false)
        cardStackView.layoutManager = CSLmanager
        mainPresenter.setTenWords()
    }

    override fun getData(words: ArrayList<Word>) {
        cardStackView.adapter = CardStackAdapter( this@MainActivity,words, this)
        cardStackView.adapter?.notifyDataSetChanged()
    }

    override fun showErrorToast(error: String) {
        Toast.makeText(this@MainActivity,error, Toast.LENGTH_SHORT).show()
    }

    override fun setRestart() {
        cardStackView.visibility = View.INVISIBLE
        binding.Continue.visibility = View.VISIBLE
        binding.cont.setOnClickListener {
            cardStackView.visibility = View.VISIBLE
            binding.Continue.visibility = View.GONE
            for (i in 1..10) {
                val resourceId =
                resources.getIdentifier("strip$i", "id",packageName)
                if (resourceId != 0) {
                    val imageView = findViewById<ImageView>(resourceId)
                    imageView.setImageResource(R.drawable.progress_unactive)
                }
            }
            binding.stats.text = "Заучено 0 новых слов"
        }
    }

    override fun setprogress(knowWords: Int) {
        binding.stats.text = "Заучено $knowWords новых слов"
        if (knowWords in 1..10) {
            val resourceId =
                resources.getIdentifier("strip$knowWords", "id", packageName)
            if (resourceId != 0) {
                val imageView = findViewById<ImageView>(resourceId)
                imageView.setImageResource(R.drawable.progress_active)
            }
        }
    }

    override fun onCardSwiped(direction: Direction?) {
        mainPresenter.cardSwipe(direction)
    }

    override fun onButtonRightClick() {
        val setting = SwipeAnimationSetting.Builder()
            .setDirection(Direction.Right)
            .setDuration(Duration.Normal.duration)
            .setInterpolator(AccelerateInterpolator())
            .build()
        CSLmanager.setSwipeAnimationSetting(setting)
        cardStackView.swipe()
    }

    override fun onButtonLeftClick() {
        val setting = SwipeAnimationSetting.Builder()
            .setDirection(Direction.Left)
            .setDuration(Duration.Normal.duration)
            .setInterpolator(AccelerateInterpolator())
            .build()
        CSLmanager.setSwipeAnimationSetting(setting)
        cardStackView.swipe()
    }
    override fun onCardDragging(direction: Direction?, ratio: Float) {}

    override fun onCardRewound() {}

    override fun onCardCanceled() {}

    override fun onCardAppeared(view: View?, position: Int) {}

    override fun onCardDisappeared(view: View?, position: Int) {}


}