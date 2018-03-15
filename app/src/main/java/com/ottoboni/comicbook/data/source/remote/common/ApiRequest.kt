package com.ottoboni.comicbook.data.source.remote.common

import com.ottoboni.comicbook.data.model.Collection
import com.ottoboni.comicbook.data.model.Publishing
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by caoj on 04/03/18.
 */
interface ApiRequest {

    @GET("/collections")
    fun getCollections(): Observable<List<Collection>>

    @GET("/publishing/{publishing_id}")
    fun getPublishing(@Path("publishing_id") id: Int): Single<Publishing>
}