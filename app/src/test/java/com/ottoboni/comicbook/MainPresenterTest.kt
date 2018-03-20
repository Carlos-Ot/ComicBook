package com.ottoboni.comicbook

import com.ottoboni.comicbook.data.model.Collection
import com.ottoboni.comicbook.data.repositories.CollectionRepository
import com.ottoboni.comicbook.features.main.MainPresenter
import com.ottoboni.comicbook.features.main.MainView
import io.reactivex.Flowable
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Test
import org.mockito.InOrder
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

/**
 * Created by caoj on 15/03/18.
 */
class MainPresenterTest {

    @Mock
    private lateinit var collectionRepository: CollectionRepository

    @Mock
    private lateinit var mainView: MainView

    @Mock
    private lateinit var collection: Collection

    private lateinit var collections: MutableList<Collection>

    private lateinit var presenter: MainPresenter

    @Before
    fun setupCollectionsPresenter() {
        MockitoAnnotations.initMocks(this)

        presenter = MainPresenter(mainView, collectionRepository)

        collections = mutableListOf<Collection>()
        for (i in 1..10) {
            collections.add(collection)
        }

        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline()}

    }

    @Test
    fun loadCollectionsAndShow() {
        `when`(collectionRepository.getCollections()).thenReturn(Flowable.just(collections))

        presenter.loadCollections(true)

        var inOrder = inOrder(mainView)
        inOrder.verify(mainView).setProgressIndicator(true)
        inOrder.verify(mainView).setProgressIndicator(false)
    }

    @Test
    fun handleItemClick() {

        presenter.handleItemClick(collection)

        verify(mainView).callCollectionDetail(collection.collectionId)
    }
}