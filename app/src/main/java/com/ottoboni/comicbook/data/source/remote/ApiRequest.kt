package com.ottoboni.comicbook.data.source.remote

import com.ottoboni.comicbook.data.source.model.Collection
import io.reactivex.Observable
import retrofit2.http.GET

/**
 * Created by caoj on 04/03/18.
 */
interface ApiRequest {

    @GET("/collections")
    fun getCollections(): Observable<List<Collection>>
}