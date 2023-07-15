package com.example.lerword_mvp.Presenters

import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.lerword.Model.Database.UserRepository
import com.example.lerword_mvp.Model.User
import com.example.lerword_mvp.views.LoginView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/*
 * Created by Skyain1 on 15.07.2023.
 */

@InjectViewState
class LoginPresenter () : MvpPresenter<LoginView>() {

    init {
        Log.d("fatal","init loginPres")
    }

    fun onButtonClicked(user: User,repository: UserRepository){
        GlobalScope.launch(Dispatchers.Main) {
            var isHave = false
            withContext(Dispatchers.IO) {
                isHave = repository.checkField(user)
            }
            if (isHave) {
                viewState.successfulAuthorization()
            }else{
                viewState.showErrorToast("Ошибка входа")
            }
        }
    }
}