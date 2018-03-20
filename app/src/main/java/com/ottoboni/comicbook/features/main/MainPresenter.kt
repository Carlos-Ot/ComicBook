package com.ottoboni.comicbook.features.main

import android.util.Log
import com.ottoboni.comicbook.data.model.Collection
import com.ottoboni.comicbook.data.repositories.CollectionRepository
import com.ottoboni.comicbook.features.common.BasePresenter
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


class MainPresenter(private val view: MainView, private val repository: CollectionRepository) : BasePresenter {

    private val compositeDisposable = CompositeDisposable()

    init {
        view.presenter = this
    }

    fun loadCollections(forceUpdate: Boolean) {

        view.setProgressIndicator(true)

        if (forceUpdate) {
            repository.refreshCollections()
        }

        compositeDisposable.clear()

        val disposable = repository.getCollections()
                .flatMap { collections: List<Collection> -> Flowable.fromIterable(collections) }
                .flatMap { collection: Collection ->
                    Log.d("xablau", "PRESENTER:: Collection Name: ${collection.collectionName}")
                    Flowable.just(collection)
                }.toList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { collections: List<Collection> ->
                            view.setProgressIndicator(false)
                            view.showCollections(collections)
                        },
                        {
                            view.setProgressIndicator(false)
                            view.showEmptyData()
                        })

        compositeDisposable.add(disposable)
    }

    fun handleItemClick(collection: Collection) {
        view.callCollectionDetail(collection.collectionId)
    }

    override fun subscribe() {
        loadCollections(false)
    }

    override fun unsubscribe() {
        compositeDisposable.clear()
    }

}