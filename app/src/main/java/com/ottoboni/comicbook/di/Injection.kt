package com.ottoboni.comicbook.di

import android.content.Context
import com.ottoboni.comicbook.data.repositories.CollectionRepository
import com.ottoboni.comicbook.data.source.local.CollectionDao
import com.ottoboni.comicbook.data.source.local.CollectionLocalDataSource
import com.ottoboni.comicbook.data.source.local.ComicBookDatabase
import com.ottoboni.comicbook.data.source.remote.CollectionRemoteDataSource

/**
 * Created by caoj on 15/03/18.
 */
object Injection {

    fun providesCollectionRepository(context: Context): CollectionRepository {
        return CollectionRepository.getInstance(
                providesCollectionRemoteDataSource(),
                providesCollectionLocalDataSource(providesDatabase(context).collectionDao())
        )
    }

    private fun providesCollectionRemoteDataSource(): CollectionRemoteDataSource {
        return CollectionRemoteDataSource
    }

    private fun providesCollectionLocalDataSource(collectionDao: CollectionDao): CollectionLocalDataSource {
        return CollectionLocalDataSource.getInstance(collectionDao)
    }

    private fun providesDatabase(context: Context): ComicBookDatabase {
        return ComicBookDatabase.getInstance(context)
    }
}