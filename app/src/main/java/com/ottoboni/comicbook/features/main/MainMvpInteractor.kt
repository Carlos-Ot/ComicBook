package com.ottoboni.comicbook.features.main

import com.ottoboni.comicbook.data.model.Collection
import io.reactivex.Single


interface MainMvpInteractor {
    fun getData(): Single<List<Collection>>
    fun refreshData()
}