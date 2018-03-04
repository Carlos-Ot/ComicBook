package com.ottoboni.comicbook.data.repositories

import android.util.Log
import com.ottoboni.comicbook.data.source.model.Collection
import com.ottoboni.comicbook.data.source.remote.ServiceClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.*

/**
 * Created by caoj on 03/03/18.
 */

class MainRepository {

    private val mApiClient = ServiceClient().getApiClient()

    fun getCollections() {
        mApiClient.getCollections()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    collections: List<Collection> -> collections.forEach {
                    collection: Collection -> Log.d("ottoboni", collection.collectionName)
                }
                },{
                    error -> Log.e("ottoboni", error.message)
                })
    }

}