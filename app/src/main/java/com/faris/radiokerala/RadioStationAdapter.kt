package com.faris.radiokerala

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RadioStationAdapter(
    private val stations: List<RadioStation>,
    private val onClick: (Int) -> Unit
) : RecyclerView.Adapter<RadioStationAdapter.ViewHolder>() {

    var currentPlayingPosition: Int? = null
        private set

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val radioName: TextView = itemView.findViewById(R.id.radioName)

        init {
            itemView.setOnClickListener {
                onClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_radio_station, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val station = stations[position]
        holder.radioName.text = station.name

        // Change color of currently playing station
        if (position == currentPlayingPosition) {
            holder.radioName.setTextColor(holder.itemView.resources.getColor(R.color.playingColor))
        } else {
            holder.radioName.setTextColor(holder.itemView.resources.getColor(R.color.defaultTextColor))
        }
    }

    override fun getItemCount() = stations.size

    fun updateCurrentPlayingPosition(position: Int?) {
        currentPlayingPosition = position
        notifyDataSetChanged()
    }
}
