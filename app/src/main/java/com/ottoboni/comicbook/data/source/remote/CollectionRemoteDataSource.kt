package com.ottoboni.comicbook.data.source.remote

import com.ottoboni.comicbook.data.model.Collection
import com.ottoboni.comicbook.data.model.Publishing
import com.ottoboni.comicbook.data.source.CollectionDataSource
import com.ottoboni.comicbook.data.source.remote.common.ServiceClient
import io.reactivex.Observable
import io.reactivex.functions.BiFunction

/**
 * Created by caoj on 03/03/18.
 */

object CollectionRemoteDataSource : CollectionDataSource {

    private val apiClient = ServiceClient().getApiClient()

    override fun getCollections(): Observable<List<Collection>> {
       return apiClient.getCollections()
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
    }

    override fun saveCollection(collection: Collection) {
        //Not required, the client is not allowed to save new Collections
    }

    override fun refreshCollections() {
        //Not required, all the logic were handled by the {@link CollectionRepository}
    }

}