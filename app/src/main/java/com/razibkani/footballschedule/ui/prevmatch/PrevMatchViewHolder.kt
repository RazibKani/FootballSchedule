package com.razibkani.footballschedule.ui.prevmatch

import android.support.v7.widget.RecyclerView
import android.view.View
import com.razibkani.footballschedule.data.model.Event
import com.razibkani.footballschedule.utils.OnItemClickListener
import com.razibkani.footballschedule.utils.setFormattedDate
import kotlinx.android.synthetic.main.item_prev_match.view.*

class PrevMatchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(event: Event, onClickListener: OnItemClickListener<Event>?) {
        itemView.textEventDate.setFormattedDate(event.dateEvent)
        itemView.textHomeTeam.text = event.strHomeTeam
        itemView.textScoreHome.text = event.intHomeScore
        itemView.textAwayTeam.text = event.strAwayTeam
        itemView.textScoreAway.text = event.intAwayScore

        onClickListener?.let {
            itemView.setOnClickListener { onClickListener.onClick(event) }
        }
    }
}