package com.razibkani.footballschedule.ui.prevmatch

import com.razibkani.footballschedule.data.model.Event
import com.razibkani.footballschedule.ui.base.MvpView

interface PrevMatchListMvpView : MvpView {

    fun showLoading()

    fun hideLoading()

    fun showErrorMessage(message: String)

    fun updateData(eventList: List<Event>)
}