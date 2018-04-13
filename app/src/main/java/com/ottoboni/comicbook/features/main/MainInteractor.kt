package com.ottoboni.comicbook.features.main

import android.util.Log
import com.ottoboni.comicbook.data.model.Collection
import com.ottoboni.comicbook.data.repositories.CollectionRepository
import io.reactivex.Flowable
import io.reactivex.Single


class MainInteractor(val repository: CollectionRepository) : MainMvpInteractor {

    override fun getData(): Single<List<Collection>> {
        return repository.getCollections()
                .flatMap { collections: List<Collection> -> Flowable.fromIterable(collections) }
                .flatMap { collection: Collection ->
                    Log.d("xablau", "PRESENTER:: Collection Name: ${collection.collectionName}")
                    Flowable.just(collection)
                }.toList()
    }

    override fun refreshData() {
    }

}