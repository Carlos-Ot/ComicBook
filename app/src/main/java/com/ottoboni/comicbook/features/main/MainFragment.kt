package com.ottoboni.comicbook.features.main

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import com.ottoboni.comicbook.R
import com.ottoboni.comicbook.data.model.Collection
import com.ottoboni.comicbook.features.collection.CollectionDetail
import kotlinx.android.synthetic.main.fragment_main.*

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
            listAdapter = MainListAdapter(root.context, emptyList(), itemClickListener)
            findViewById<RecyclerView>(R.id.collection_list).apply {
                adapter = listAdapter
                layoutManager = LinearLayoutManager(requireContext(), LinearLayout.VERTICAL, false)
            }
        }

        return root
    }


    val itemClickListener: (View, Int, Int) -> Unit = {view, position, type ->
        Toast.makeText(requireContext(), "Collection Name: ${listAdapter.collections.get(position).collectionName}", Toast.LENGTH_LONG).show()
        presenter.handleItemClick(listAdapter.collections.get(position))
    }

    override fun onResume() {
        super.onResume()
        presenter.loadCollections(false)
    }

    override fun setProgressIndicator(active: Boolean) {
        val visibility = if (active) View.VISIBLE else View.GONE

        progress_indicator.visibility = visibility
        label_progress_loading.visibility = visibility
    }

    override fun showCollections(collections: List<Collection>) {
        collection_list.visibility = View.VISIBLE
        listAdapter.collections = collections
    }

    override fun showEmptyData() {
    }

    override fun callCollectionDetail(collectionId: String) {
        val intent = Intent(requireContext(), CollectionDetail::class.java)
        startActivity(intent)
    }

}