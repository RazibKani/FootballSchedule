package com.razibkani.footballschedule.ui.nextmatch

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.razibkani.footballschedule.R
import com.razibkani.footballschedule.data.model.Event
import com.razibkani.footballschedule.utils.OnItemClickListener
import com.razibkani.footballschedule.utils.inflate
import javax.inject.Inject

class NextMatchAdapter @Inject constructor() : RecyclerView.Adapter<NextMatchViewHolder>() {

    private var footballEvents: List<Event> = ArrayList()
    lateinit var onItemClickListener: OnItemClickListener<Event>

    fun updateData(newFootballEvents: List<Event>) {
        footballEvents = newFootballEvents
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NextMatchViewHolder {
        val view = parent.inflate(R.layout.item_next_match)
        return NextMatchViewHolder(view)
    }

    override fun getItemCount(): Int {
        return footballEvents.size
    }

    override fun onBindViewHolder(holder: NextMatchViewHolder, position: Int) {
        holder.bind(footballEvents[position], onItemClickListener)
    }
}