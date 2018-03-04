package com.ottoboni.comicbook.data.source.model

import com.google.gson.annotations.SerializedName

data class Collection constructor(
        @SerializedName("collecion_id") var collectionId: String,
        @SerializedName("collection_name") var collectionName:String,
        @SerializedName("collection_url") var collectionUrl: String,
        @SerializedName("status") var status: Int,
        @SerializedName("publishing") var publishing: Int,
        @SerializedName("last_modified") var lastModified: String

)