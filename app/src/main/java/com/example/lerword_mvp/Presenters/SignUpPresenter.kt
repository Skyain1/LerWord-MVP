package com.example.lerword_mvp.Presenters

import android.util.Patterns
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.lerword.Model.Database.UserRepository
import com.example.lerword_mvp.Model.User
import com.example.lerword_mvp.views.SignUpView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/*
 * Created by Skyain1 on 15.07.2023.
 */

@InjectViewState
class SignUpPresenter():MvpPresenter<SignUpView>() {

    fun onButtonClicked(email: String, password: String, confirmpas: String,repository : UserRepository) {
        val user = User(email = email, password = password)

        if (validateInput(email, password, confirmpas)) {
            GlobalScope.launch(Dispatchers.Main) {
                withContext(Dispatchers.IO) {
                    repository.insert(user)
                }
                viewState.successfulRegistration()
            }
        } else {
            viewState.showErrorToast("Ошибка регистрации")
        }
    }

    private fun validateInput(email: String, password: String, confirmpas: String): Boolean {
        if (email.isNotEmpty() && password.isNotEmpty() && confirmpas.isNotEmpty()
        ) {
            if (Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                if (password.equals(confirmpas)) {
                    return true
                } else {
                    viewState.showErrorToast("Пароли не совпадают")
                }
            } else {
                viewState.showErrorToast("Почта введена не верно")
            }
        }
        return false
    }
}