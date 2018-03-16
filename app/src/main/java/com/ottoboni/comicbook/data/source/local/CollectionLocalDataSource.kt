package com.ottoboni.comicbook.data.source.local

import android.content.Context
import android.util.Log
import com.ottoboni.comicbook.data.model.Collection
import com.ottoboni.comicbook.data.source.CollectionDataSource
import io.reactivex.Flowable
import io.reactivex.Observable

/**
 * Created by caoj on 15/03/18.
 */
class CollectionLocalDataSource private constructor(
        private val collectionDao: CollectionDao
) : CollectionDataSource {

    override fun getCollections(): Flowable<List<Collection>> {

        return collectionDao.getCollections()
    }

    override fun saveCollection(collection: Collection) {
        collectionDao.insertCollection(collection)
    }

    override fun refreshCollections() {
        //Not required, all the logic were handled by the {@link CollectionRepository}
    }

    companion object {

        private var instance: CollectionLocalDataSource? = null

        @JvmStatic
        fun getInstance(collectionDao: CollectionDao): CollectionLocalDataSource {
            if (instance == null) {
                synchronized(CollectionLocalDataSource::javaClass) {
                    instance = CollectionLocalDataSource(collectionDao)
                }
            }
            return instance!!
        }
    }

}