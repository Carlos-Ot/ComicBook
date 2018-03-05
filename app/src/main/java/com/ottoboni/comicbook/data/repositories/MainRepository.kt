package com.ottoboni.comicbook.data.repositories

import android.util.Log
import com.ottoboni.comicbook.data.model.Collection
import com.ottoboni.comicbook.data.model.Publishing
import com.ottoboni.comicbook.data.source.remote.ServiceClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by caoj on 03/03/18.
 */

class MainRepository{

    private val apiClient = ServiceClient().getApiClient()

    fun getCollections(callback: RequestCallback) {
        apiClient.getCollections()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    collections: List<Collection> -> callback.onSuccess(collections)
                },{
                    error -> Log.e("xablau", error.message)
                    callback.onError()
                })
    }

    interface RequestCallback {
        fun onSuccess(collections: List<Collection>)
        fun onError()
    }

}