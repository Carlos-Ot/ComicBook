package com.ottoboni.comicbook.data.repositories

import com.ottoboni.comicbook.data.model.Collection
import com.ottoboni.comicbook.data.source.CollectionDataSource
import io.reactivex.Observable

/**
 * Created by caoj on 15/03/18.
 */
class CollectionRepository(
       private val collectionRemoteDataSource: CollectionDataSource,
       private val collectionLocalDataSource: CollectionDataSource
) : CollectionDataSource {

    private var forceUpdate = false

    /**
     * Return single instance of CollectionRepository
     */
    companion object {

        private var instance: CollectionRepository? = null

        @JvmStatic fun getInstance(
                collectionRemoteDataSource: CollectionDataSource,
                collectionLocalDataSource: CollectionDataSource
                ): CollectionRepository {
            return instance ?: getInstance(collectionRemoteDataSource, collectionLocalDataSource)
                    .apply { instance = this }

        }
    }

    override fun getCollections(): Observable<List<Collection>> {

        if (forceUpdate) {
            return getAndSaveRemoteCollections()
        } else {
            return getAndSaveLocalCollections()
        }
    }

    override fun refreshCollections() {
        forceUpdate = true
    }

    override fun saveCollection(collection: Collection) {
        collectionLocalDataSource.saveCollection(collection)
    }

    private fun getAndSaveRemoteCollections() : Observable<List<Collection>> {
        return collectionRemoteDataSource
                .getCollections()
                .flatMap { collections: List<Collection> ->
                    Observable.fromIterable(collections)
                            .doOnNext { collection: Collection ->
                                collectionLocalDataSource.saveCollection(collection)
                            }
                }.toList().toObservable()
                .doOnComplete { forceUpdate = false }
    }

    private fun getAndSaveLocalCollections() : Observable<List<Collection>> {
        return collectionLocalDataSource
                .getCollections()
                .flatMap { collections: List<Collection> ->
                    Observable.fromIterable(collections)
                            .doOnNext { collection: Collection ->
                                //Do something with data
                            }
                }.toList().toObservable()
    }

}