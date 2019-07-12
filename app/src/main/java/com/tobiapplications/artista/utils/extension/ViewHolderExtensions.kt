package com.tobiapplications.artista.utils.extension

import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

fun RecyclerView.ViewHolder.getColor(res: Int) : Int {
    return ContextCompat.getColor(itemView.context, res)
}