package com.ottoboni.comicbook.features.main

import android.support.v4.app.Fragment

/**
 * Created by caoj on 18/02/18.
 */
class MainFragment : Fragment(), MainView{

    override lateinit var mPresenter: MainPresenter

    companion object {
        fun newInstance() = MainFragment();
    }

}