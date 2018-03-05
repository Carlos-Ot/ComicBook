package com.ottoboni.comicbook.features.main

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.ottoboni.comicbook.R
import com.ottoboni.comicbook.data.model.Collection


/**
 * Created by caoj on 05/03/18.
 */
class MainListAdapter constructor(val context: Context, items: List<Collection>) : RecyclerView.Adapter<MainListAdapter.ViewHolder>() {

    var collections: List<Collection> = items
        set(items) {
            field = items
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.collection_list_item, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return collections.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(collections.get(position))
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindView(collection: Collection) {
            val collectionName = itemView.findViewById<TextView>(R.id.txt_collection_name)
            val imageView = itemView.findViewById<ImageView>(R.id.img_collection_icon)

            collectionName.text = collection.collectionName
            imageView.visibility = View.VISIBLE
        }
    }
}