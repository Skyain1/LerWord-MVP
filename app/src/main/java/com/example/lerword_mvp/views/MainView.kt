package com.example.lerword_mvp.views

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.example.lerword_mvp.Model.Word

/*
 * Created by Skyain1 on 15.07.2023.
 */

@StateStrategyType(value = AddToEndSingleStrategy ::class)
interface MainView : MvpView{
    fun getData(words : ArrayList<Word>)
    fun showErrorToast(error: String)
    fun setprogress(knowWords:Int)

    @StateStrategyType(value = SkipStrategy ::class)
    fun setRestart()
}