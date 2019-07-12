package com.tobiapplications.artista.ui.viewhandler

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AdapterDelegatesManager
import com.tobiapplications.artista.utils.general.DisplayableItem

open class BaseRecyclerViewAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var itemList : MutableList<DisplayableItem> = ArrayList()
    var delegatesManager: AdapterDelegatesManager<List<DisplayableItem>> = AdapterDelegatesManager()

    companion object {
        const val INVALID_INDEX = -1
    }

    /** ViewHolder Methods **/

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return delegatesManager.onCreateViewHolder(parent, viewType)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        delegatesManager.onBindViewHolder(itemList, position, holder)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun getItemViewType(position: Int): Int {
        return delegatesManager.getItemViewType(itemList, position)
    }

    /** Convenience Methods **/

    fun setItems(items: List<DisplayableItem>?) {
        items?.let { itemList.addAll(it) }
        notifyDataSetChanged()
    }

    fun addItems(items: List<DisplayableItem>?) {
        if (items == null) {
            return
        }

        val currentSize = itemCount
        val newItems = items.filter { !itemList.contains(it) }
        itemList.addAll(newItems)
        notifyItemRangeInserted(currentSize, newItems.size)
    }

    fun clear() {
        itemList.clear()
        notifyDataSetChanged()
    }

    fun indexOf(item: DisplayableItem?): Int {
        return if (itemList.contains(item)) { itemList.indexOf(item) } else INVALID_INDEX
    }
}



