package com.ottoboni.comicbook.data.source.local

import android.content.Context
import com.ottoboni.comicbook.data.model.Collection
import com.ottoboni.comicbook.data.source.CollectionDataSource
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

/**
 * Created by caoj on 15/03/18.
 */
class CollectionLocalDatasource private constructor(
        val context: Context
): CollectionDataSource {

    override fun getCollections(): Observable<List<Collection>> {

        return ComicBookDatabase.getInstance(context).collectionDao()
                .getCollections()
                .toObservable()
    }

    override fun saveCollection(collection: Collection) {
        ComicBookDatabase.getInstance(context).collectionDao().insertCollection(collection)
    }

    override fun refreshCollections() {
        //Not required, all the logic were handled by the {@link CollectionRepository}
    }

}