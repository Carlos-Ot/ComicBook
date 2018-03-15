package com.ottoboni.comicbook.data.model

import com.google.gson.annotations.SerializedName

/**
 * Created by caoj on 05/03/18.
 */
data class Publishing constructor (
        @SerializedName("publishing_id") val id: Int,
        @SerializedName("name") val publishingName: String
)