package com.ottoboni.comicbook

import com.ottoboni.comicbook.data.model.Collection
import com.ottoboni.comicbook.data.repositories.CollectionRepository
import com.ottoboni.comicbook.features.main.MainPresenter
import com.ottoboni.comicbook.features.main.MainView
import io.reactivex.Flowable
import io.reactivex.Scheduler
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.internal.schedulers.ExecutorScheduler
import io.reactivex.plugins.RxJavaPlugins
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import java.util.concurrent.Executor
import java.util.concurrent.TimeUnit

/**
 * Created by caoj on 15/03/18.
 */
class MainPresenterTest {

    @Mock
    private lateinit var collectionRepository: CollectionRepository

    @Mock
    private lateinit var mainView: MainView

    private lateinit var presenter: MainPresenter

    private val immediate = object : Scheduler() {
        override fun scheduleDirect(run: Runnable, delay: Long, unit: TimeUnit) = super.scheduleDirect(run, 0, unit)
        override fun createWorker() = ExecutorScheduler.ExecutorWorker(Executor { it.run() })
    }

    @Before
    fun setupCollectionsPresenter() {
        MockitoAnnotations.initMocks(this)

        presenter = MainPresenter(mainView, collectionRepository)

        RxAndroidPlugins.setInitMainThreadSchedulerHandler { immediate }
        RxJavaPlugins.setInitIoSchedulerHandler { immediate }
    }

    @Test
    fun loadCollectionsAndShow() {

        val collections = listOf(
                Collection("1", "Collection 1", "collection_url_1", 1, 1, "last_modified_1"),
                Collection("2", "Collection 2", "collection_url_2", 2, 2, "last_modified_2"),
                Collection("3", "Collection 3", "collection_url_3", 3, 3, "last_modified_3")
        )

        `when`(collectionRepository.getCollections()).thenReturn(Flowable.just(collections))

        presenter.loadCollections(true)

        var inOrder = inOrder(mainView)
        inOrder.verify(mainView).setProgressIndicator(true)
        inOrder.verify(mainView).setProgressIndicator(false)
        inOrder.verify(mainView).showCollections(collections)
    }

    @Test
    fun loadCollectionsOnError() {

        `when`(collectionRepository.getCollections()).thenReturn(Flowable.error<List<Collection>>(Exception()))

        presenter.loadCollections(true)

        var inOrder = inOrder(mainView)
        inOrder.verify(mainView).setProgressIndicator(true)
        inOrder.verify(mainView).setProgressIndicator(false)
        inOrder.verify(mainView).showEmptyData()
    }

    @Test
    fun handleItemClick() {

        val collection = Collection("1", "Collection 1", "collection_url_1", 1, 1, "last_modified_1")

        presenter.handleItemClick(collection)

        verify(mainView).callCollectionDetail(collection.collectionId)
    }
}