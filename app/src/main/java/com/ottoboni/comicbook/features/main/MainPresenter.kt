package com.ottoboni.comicbook.features.main

import com.ottoboni.comicbook.data.repositories.MainRepository


class MainPresenter(private val mView: MainView, private val mRepository: MainRepository) {

    init {
        mView.mPresenter = this
    }

    fun getCollections() {
        mRepository.getCollections()
    }

}