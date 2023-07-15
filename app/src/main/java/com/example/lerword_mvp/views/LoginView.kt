package com.example.lerword_mvp.views

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

/*
 * Created by Skyain1 on 15.07.2023.
 */

@StateStrategyType(value = AddToEndStrategy::class)
interface LoginView : MvpView {
    fun successfulAuthorization()
    fun showErrorToast(error: String)
}