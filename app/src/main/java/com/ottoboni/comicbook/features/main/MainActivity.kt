package com.ottoboni.comicbook.features.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.ottoboni.comicbook.R
import com.ottoboni.comicbook.di.Injection
import com.ottoboni.comicbook.util.extensions.replaceFragment

class MainActivity : AppCompatActivity() {

    private lateinit var mainPresenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mainFragment = supportFragmentManager.findFragmentById(R.id.fragment_holder)
        as MainFragment? ?: MainFragment.newInstance().also {
            replaceFragment(it, R.id.fragment_holder)
        }

        mainPresenter = MainPresenter(view = mainFragment,
                interactor = MainInteractor(Injection.providesCollectionRepository()))

    }
}
