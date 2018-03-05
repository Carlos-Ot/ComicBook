package com.ottoboni.comicbook.data.model

import com.google.gson.annotations.SerializedName

/**
 * Created by caoj on 05/03/18.
 */
data class Publishing constructor (
        @SerializedName("publishing_id") var id: Int,
        @SerializedName("name") var publishingName: String
)