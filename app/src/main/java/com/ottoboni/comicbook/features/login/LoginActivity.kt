package com.ottoboni.comicbook.features.login

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.ottoboni.comicbook.R
import com.ottoboni.comicbook.di.Injection
import com.ottoboni.comicbook.util.extensions.replaceFragment

class LoginActivity : AppCompatActivity() {

    private lateinit var loginPresenter: LoginPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val loginFragment = supportFragmentManager.findFragmentById(R.id.fragment_holder)
        as LoginFragment? ?: LoginFragment.newInstance().also {
            replaceFragment(it, R.id.fragment_holder)
        }

        loginPresenter = LoginPresenter(loginFragment)
    }
}
