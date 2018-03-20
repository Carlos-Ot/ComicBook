package com.ottoboni.comicbook.data.repositories

import com.ottoboni.comicbook.data.model.Collection
import com.ottoboni.comicbook.data.source.CollectionDataSource
import io.reactivex.Flowable

/**
 * Created by caoj on 15/03/18.
 */
class CollectionRepository(
       val collectionRemoteDataSource: CollectionDataSource,
       val collectionLocalDataSource: CollectionDataSource
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
            return instance ?: CollectionRepository(collectionRemoteDataSource, collectionLocalDataSource)
                    .apply { instance = this }

        }
    }

    override fun getCollections(): Flowable<List<Collection>> {

        val remoteCollections = getAndSaveRemoteCollections()

        if (forceUpdate) {
            return remoteCollections
        } else {
            val localCollections = getAndSaveLocalCollections()

            return Flowable.concat(localCollections, remoteCollections)
                    .firstOrError()
                    .toFlowable()
        }
    }

    override fun refreshCollections() {
        forceUpdate = true
    }

    override fun saveCollection(collection: Collection) {
        collectionLocalDataSource.saveCollection(collection)
    }

    private fun getAndSaveRemoteCollections(): Flowable<List<Collection>> {
        return collectionRemoteDataSource
                .getCollections()
                .flatMap { collections: List<Collection> ->
                    Flowable.fromIterable(collections)
                            .doOnNext { collection: Collection ->
                                collectionLocalDataSource.saveCollection(collection)
                            }
                }.toList().toFlowable()
                .doOnComplete { forceUpdate = false }
    }

    private fun getAndSaveLocalCollections(): Flowable<List<Collection>> {
        return collectionLocalDataSource
                .getCollections()
    }

}