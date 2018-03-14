package com.ottoboni.comicbook.features.main

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.ottoboni.comicbook.R
import com.ottoboni.comicbook.data.model.Collection

/**
 * Created by caoj on 18/02/18.
 */
class MainFragment : Fragment(), MainView {

    override lateinit var presenter: MainPresenter

    private lateinit var listAdapter: MainListAdapter

    companion object {
        fun newInstance() = MainFragment();
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val root = inflater.inflate(R.layout.fragment_main, container, false)

        with(root) {
            listAdapter = MainListAdapter(root.context, emptyList())
            findViewById<RecyclerView>(R.id.collection_list).apply {
                adapter = listAdapter
                layoutManager = LinearLayoutManager(requireContext(), LinearLayout.VERTICAL, false)
            }
        }

        return root
    }

    override fun onResume() {
        super.onResume()
        presenter.getCollections()
    }

    override fun showCollections(collections: List<Collection>) {
        listAdapter.collections = collections
    }

    override fun showEmptyData() {
    }

}