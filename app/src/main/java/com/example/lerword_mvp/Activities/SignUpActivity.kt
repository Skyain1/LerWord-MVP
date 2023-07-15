package com.example.lerword_mvp.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.arellomobile.mvp.MvpActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.lerword.Model.Database.UserDao
import com.example.lerword.Model.Database.UserDatabase
import com.example.lerword.Model.Database.UserRepository
import com.example.lerword_mvp.Model.User
import com.example.lerword_mvp.Presenters.LoginPresenter
import com.example.lerword_mvp.Presenters.SignUpPresenter
import com.example.lerword_mvp.R
import com.example.lerword_mvp.databinding.ActivityLoginBinding
import com.example.lerword_mvp.databinding.ActivitySignUpBinding
import com.example.lerword_mvp.views.SignUpView

/*
 * Created by Skyain1 on 15.07.2023.
 */

class SignUpActivity : MvpActivity(),SignUpView {

    private lateinit var binding: ActivitySignUpBinding

    @InjectPresenter
    lateinit var signUpPresenter: SignUpPresenter

    private lateinit var dao: UserDao
    private lateinit var repository: UserRepository


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        dao = UserDatabase.getDatabase(this@SignUpActivity).getUserDao()
        repository = UserRepository(dao)

        binding.enter.setOnClickListener {
            signUpPresenter.onButtonClicked(binding.email.text.toString(), binding.password.text.toString(),binding.confirmpass.text.toString(),repository)
        }

        binding.toLogin.setOnClickListener {
            startActivity(Intent(this@SignUpActivity, LoginActivity::class.java))
        }

    }

    override fun successfulRegistration() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }

    override fun showErrorToast(error: String) {
        Toast.makeText(this@SignUpActivity, error, Toast.LENGTH_SHORT).show()
    }
}