package com.razibkani.footballschedule.ui.nextmatch

import com.razibkani.footballschedule.data.model.Event
import com.razibkani.footballschedule.ui.base.MvpView

interface NextMatchMvpView : MvpView {

    fun showLoading()

    fun hideLoading()

    fun showErrorMessage(message: String)

    fun updateData(data: List<Event>)
}