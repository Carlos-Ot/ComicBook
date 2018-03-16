package com.ottoboni.comicbook.data.source.local

import android.arch.persistence.room.*
import com.ottoboni.comicbook.data.model.Collection
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single

/**
 * Created by caoj on 15/03/18.
 */
@Dao interface CollectionDao {

    @Query("SELECT * FROM Collections")
    fun getCollections(): Flowable<List<Collection>>

    @Query("SELECT * FROM Collections WHERE collection_id = :collectionId")
    fun getCollectionById(collectionId: String): Single<Collection>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCollection(collection: Collection)

    @Update
    fun updateCollection(collection: Collection): Int

    @Query("DELETE FROM Collections WHERE collection_id = :collectionId")
    fun deleteCollectionById(collectionId: String): Int

    @Query("DELETE FROM Collections")
    fun deleteCollections()
}