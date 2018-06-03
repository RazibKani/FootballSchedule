package com.razibkani.footballschedule.ui.prevmatch

import com.razibkani.footballschedule.data.DataManager
import com.razibkani.footballschedule.ui.base.BasePresenter
import com.razibkani.footballschedule.utils.CoroutineContextProvider
import kotlinx.coroutines.experimental.async
import javax.inject.Inject

class PrevMatchListPresenter @Inject constructor(private val dataManager: DataManager,
                                                 private val context: CoroutineContextProvider) : BasePresenter<PrevMatchListMvpView>() {

    fun getEvents() {
        mvpView?.showLoading()

        async(context.main) {
            val data = dataManager.getFootballPrevEvent()

            mvpView?.apply {
                if (data.events.isNotEmpty()) {
                    this.updateData(data.events)
                } else {
                    this.showErrorMessage("Event Kosong")
                }
                this.hideLoading()
            }
        }
    }
}