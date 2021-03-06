package com.ottoboni.comicbook.data.source

import com.ottoboni.comicbook.data.model.Collection
import io.reactivex.Flowable
import io.reactivex.Observable

/**
 * Created by caoj on 15/03/18.
 */
interface CollectionDataSource {

    fun getCollections(): Flowable<List<Collection>>

    fun refreshCollections()

    fun saveCollection(collection: Collection)
}