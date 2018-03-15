package com.ottoboni.comicbook.features.main

import com.ottoboni.comicbook.data.model.Collection
import com.ottoboni.comicbook.data.repositories.CollectionRepository
import com.ottoboni.comicbook.data.source.remote.CollectionRemoteDataSource
import com.ottoboni.comicbook.features.common.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


class MainPresenter(private val view: MainView, private val repository: CollectionRepository): BasePresenter {

    private val compositeDisposable = CompositeDisposable()

    init {
        view.presenter = this
    }

    fun loadCollections(forceUpdate: Boolean) {

        if (forceUpdate) {
            repository.refreshCollections()
        }

        compositeDisposable.clear()

        val disposable = repository.getCollections()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { collections: List<Collection> ->
                            view.showCollections(collections)
                        },
                        {
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