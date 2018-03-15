package com.ottoboni.comicbook.util.extensions

import android.support.v7.widget.RecyclerView
import android.view.View

/**
 * Created by caoj on 13/03/18.
 */
fun <T : RecyclerView.ViewHolder> T.onClick(event: (view: View, position: Int, type: Int) -> Unit): T {
    itemView.setOnClickListener {
        event.invoke(it, adapterPosition, itemViewType)
    }
    return this
}