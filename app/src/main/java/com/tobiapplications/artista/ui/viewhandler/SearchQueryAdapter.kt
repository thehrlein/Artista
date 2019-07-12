package com.tobiapplications.artista.ui.viewhandler

import android.content.Context
import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableString
import android.text.style.StyleSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Filter
import android.widget.TextView
import com.tobiapplications.artista.R
import com.tobiapplications.artista.model.searchartist.Artist
import com.tobiapplications.artista.ui.views.SearchItemView
import com.tobiapplications.artista.utils.general.ArtistaConstants
import java.text.Normalizer
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.min

class SearchQueryAdapter(context: Context, private val itemRes: Int = R.layout.view_search_query_single_item_view, objects: List<Artist>) : ArrayAdapter<Artist>(context, itemRes, objects) {

    private var artists : MutableList<Artist>
    private var prefix = ArtistaConstants.DEFAULT_STRING

    init {
        this.artists = objects.toMutableList()
        notifyDataSetChanged()
    }

    fun setArtists(artists: List<Artist>) {
        this.artists = artists.toMutableList()
        notifyDataSetChanged()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view = convertView

        val viewHolder: SearchItemView
        val listViewItem = artists[position]

        if (view == null) {
            view = LayoutInflater.from(context).inflate(itemRes, null)

            val textView = view.findViewById<TextView>(R.id.text)
            viewHolder = SearchItemView(textView)
            view.tag = viewHolder
        } else {
            viewHolder = view.tag as SearchItemView
        }

        val highlighted = highlight(prefix, listViewItem.artist)
        viewHolder.textView.text = highlighted

        parent.isScrollbarFadingEnabled = false
        return view!!
    }

    override fun getItem(position: Int): Artist? {
        return artists[position]
    }

    override fun getCount(): Int {
        return artists.size
    }

    override fun getFilter(): Filter {
        return mFilter
    }

    private val mFilter = object : Filter() {

        override fun convertResultToString(resultValue: Any): String {
            return (resultValue as Artist).artist
        }

        override fun performFiltering(constraint: CharSequence?): FilterResults {
            val results = FilterResults()
            prefix = constraint.toString()
            val suggestions = ArrayList<String>()
            for (thing in objects) {
                // Note: change the "contains" to "startsWith" if you only want starting matches
                if (thing.artist.toLowerCase().contains(constraint.toString().toLowerCase())) {
                    suggestions.add(thing.artist)
                }
            }
            results.values = suggestions
            results.count = suggestions.size


            return results
        }

        override fun publishResults(constraint: CharSequence?, results: FilterResults) {
            artists.clear()
            if (results.count > 0) {
                artists.addAll(results.values as ArrayList<Artist>)
            }

            notifyDataSetChanged()
        }
    }

    private fun highlight(search: String, originalText: String?): CharSequence? {
        // ignore case and accents
        // the same thing should have been done for the search text
        val normalizedText = Normalizer
            .normalize(originalText, Normalizer.Form.NFD)
            .replace("\\p{InCombiningDiacriticalMarks}+".toRegex(), ArtistaConstants.DEFAULT_STRING)
            .toLowerCase(Locale.ENGLISH)

        var start = normalizedText.indexOf(search.toLowerCase(Locale.ENGLISH))
        if (start < 0) {
            // not found, nothing to to
            return originalText
        } else {
            // highlight each appearance in the original text
            // while searching in normalized text
            val styleSpan = StyleSpan(Typeface.BOLD)
            val highlighted = SpannableString(originalText)
            while (start >= 0) {
                val spanStart = min(start, originalText!!.length)
                val spanEnd = min(start + search.length, originalText.length)

                highlighted.setSpan(styleSpan,
                    spanStart, spanEnd, Spannable.SPAN_INCLUSIVE_INCLUSIVE)

                start = normalizedText.indexOf(search, spanEnd)
            }

            return highlighted
        }
    }

    fun clearCurrentArtists() {
        this.artists.clear()
        notifyDataSetChanged()
    }
}