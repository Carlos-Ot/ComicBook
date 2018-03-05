package com.ottoboni.comicbook.features.main

import com.ottoboni.comicbook.data.model.Collection
import com.ottoboni.comicbook.data.repositories.MainRepository


class MainPresenter(private val view: MainView, private val repository: MainRepository) {

    init {
        view.presenter = this
    }

    fun getCollections() {
        repository.getCollections(object : MainRepository.RequestCallback{
            override fun onSuccess(collections: List<Collection>) {
                view.showCollections(collections)
            }

            override fun onError() {
                view.showEmptyData()
            }
        })
    }

}