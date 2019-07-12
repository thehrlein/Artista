package com.tobiapplications.artista.ui.viewhandler

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.tobiapplications.artista.R
import com.tobiapplications.artista.model.tracks.Track
import com.tobiapplications.artista.utils.extension.formatTime
import com.tobiapplications.artista.utils.extension.onClick

class TracksAdapter(context: Context,
                    tracks: List<Track>,
                    private val resId: Int = R.layout.view_track_list_row,
                    private val openTrack: (String) -> Unit) : ArrayAdapter<Track>(context, resId, tracks) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        // header row
        if (position == 0) {
            return LayoutInflater.from(context).inflate(R.layout.view_track_list_header, null)
        }

        // since we add a header row, the given position does not match with track list index
        val track = getItem(position - 1)
        val view = LayoutInflater.from(context).inflate(resId, null)

        track?.let {
            view.apply {
                findViewById<TextView>(R.id.number).text = "$position."
                findViewById<TextView>(R.id.name).text = it.name
                findViewById<TextView>(R.id.duration).text = it.duration.formatTime()
                findViewById<ImageView>(R.id.openTrackUrl).onClick { openTrack(it.url) }
            }
        }

        return view
    }

    // Add one because of header row is also rendered inside listView
    override fun getCount(): Int {
        return super.getCount() + 1
    }
}