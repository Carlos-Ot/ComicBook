package com.ottoboni.comicbook.features.main

import com.ottoboni.comicbook.data.model.Collection
import com.ottoboni.comicbook.features.base.BaseView

/**
 * Created by caoj on 18/02/18.
 */
interface MainView : BaseView<MainPresenter> {
    fun setProgressIndicator(active: Boolean)
    fun showCollections(collections: List<Collection>)
    fun showEmptyData()
    fun callCollectionDetail(collectionId: String)
}