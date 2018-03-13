package com.ottoboni.comicbook.data.repositories

import android.util.Log
import com.ottoboni.comicbook.data.model.Collection
import com.ottoboni.comicbook.data.model.Publishing
import com.ottoboni.comicbook.data.source.remote.ServiceClient
import io.reactivex.Observable
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.BiFunction
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers

/**
 * Created by caoj on 03/03/18.
 */

class MainRepository {

    private val apiClient = ServiceClient().getApiClient()

    fun getCollections(callback: RequestCallback) {
        apiClient.getCollections()
                .subscribeOn(Schedulers.io())
                .flatMap { collections -> Observable.fromIterable(collections) }
                /*
                * Another approach
                *.flatMap { collection: Collection ->
                *    val publishing: Publishing = apiClient.getPublishing(collection.publishing).blockingGet()
                *    Observable.just(collection.apply { publishingName = publishing.publishingName })
                *}.toList().toObservable()
                *
                */
                .flatMap { collection: Collection ->
                    Observable.zip(
                            Observable.just(collection),
                            apiClient.getPublishing(collection.publishing).toObservable(),
                            BiFunction<Collection, Publishing, Collection> { collectionReturn, publishingReturn ->
                                collectionReturn.apply {
                                    this.publishingName = publishingReturn.publishingName
                                }
                            }
                    )
                }.toList().toObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ collections: List<Collection> ->
                    callback.onSuccess(collections)
                }, { error ->
                    Log.e("xablau", error.message)
                    callback.onError()
                })
    }

    interface RequestCallback {
        fun onSuccess(collections: List<Collection>)
        fun onError()
    }

}