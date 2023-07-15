package com.example.lerword_mvp.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.arellomobile.mvp.MvpActivity
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.lerword.Model.Database.UserDao
import com.example.lerword.Model.Database.UserDatabase
import com.example.lerword.Model.Database.UserRepository
import com.example.lerword_mvp.Model.User
import com.example.lerword_mvp.Presenters.LoginPresenter
import com.example.lerword_mvp.R
import com.example.lerword_mvp.databinding.ActivityLoginBinding
import com.example.lerword_mvp.views.LoginView

/*
 * Created by Skyain1 on 15.07.2023.
 */

class LoginActivity : MvpActivity(), LoginView {
    private lateinit var binding: ActivityLoginBinding

    @InjectPresenter
    lateinit var loginPresenter: LoginPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val dao = UserDatabase.getDatabase(this@LoginActivity).getUserDao()
        val repository = UserRepository(dao)
        binding.enter.setOnClickListener {
            val user = User(
                email = binding.email.text.toString(),
                password = binding.password.text.toString()
            )
            loginPresenter.onButtonClicked(user,repository)
        }

        binding.toSignUp.setOnClickListener {
            startActivity(Intent(this@LoginActivity, SignUpActivity::class.java))
        }
    }

    override fun successfulAuthorization() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    override fun showErrorToast(error: String) {
        Toast.makeText(this@LoginActivity, error, Toast.LENGTH_SHORT).show()
    }
}