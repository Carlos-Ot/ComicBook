package com.ottoboni.comicbook.data.source

import com.ottoboni.comicbook.data.model.Collection
import io.reactivex.Observable

/**
 * Created by caoj on 15/03/18.
 */
interface CollectionDataSource {

    fun getCollections(): Observable<List<Collection>>

    fun refreshCollections()

    fun saveCollection(collection: Collection)
}