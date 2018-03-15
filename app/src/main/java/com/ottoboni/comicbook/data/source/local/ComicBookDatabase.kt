package com.ottoboni.comicbook.data.source.local

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.ottoboni.comicbook.data.model.Collection

/**
 * Created by caoj on 15/03/18.
 */
@Database(entities = arrayOf(Collection::class), version = 1)
abstract class ComicBookDatabase : RoomDatabase() {
    abstract fun collectionDao() : CollectionDao

    companion object {

        private var instance: ComicBookDatabase? = null

        private val lock = Any()

        fun getInstance(context: Context): ComicBookDatabase {
            synchronized(lock) {
                if (instance == null) {
                    instance = Room.databaseBuilder(context.applicationContext,
                            ComicBookDatabase::class.java, "ComicBook.db")
                            .build()
                }

                return instance!!
            }
        }
    }
}