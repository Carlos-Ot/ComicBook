package com.ottoboni.comicbook.data.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Collections")
data class Collection constructor(
        @PrimaryKey
        @SerializedName("collection_id")
        @ColumnInfo(name = "collection_id")
        val collectionId: String,

        @ColumnInfo(name = "collection_name")
        @SerializedName("collection_name")
        val collectionName:String,

        @ColumnInfo(name = "collection_url")
        @SerializedName("collection_url")
        val collectionUrl: String,

        @ColumnInfo(name = "status")
        @SerializedName("status")
        val status: Int,

        @ColumnInfo(name = "publishing_id")
        @SerializedName("publishing")
        val publishing: Int,

        @ColumnInfo(name = "last_modified")
        @SerializedName("last_modified")
        val lastModified: String
) {
        @ColumnInfo(name = "publishing_name")
        var publishingName: String = ""
}