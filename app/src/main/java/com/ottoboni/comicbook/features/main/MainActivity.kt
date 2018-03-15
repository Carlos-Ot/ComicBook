package com.ottoboni.comicbook.features.main

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.ottoboni.comicbook.R
import com.ottoboni.comicbook.data.source.remote.CollectionRemoteDataSource
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

        mainPresenter = MainPresenter(mainFragment, CollectionRemoteDataSource())
    }
}
