package com.razibkani.footballschedule.ui.nextmatch

import com.razibkani.footballschedule.data.DataManager
import com.razibkani.footballschedule.ui.base.BasePresenter
import com.razibkani.footballschedule.utils.CoroutineContextProvider
import kotlinx.coroutines.experimental.async
import javax.inject.Inject

class NextMatchPresenter @Inject constructor(private val dataManager: DataManager,
                                             private val context: CoroutineContextProvider) : BasePresenter<NextMatchMvpView>() {

    fun getEvents() {
        mvpView?.showLoading()

        async(context.main) {
            val data = dataManager.getFootballNextEvent()

            mvpView?.apply {
                if (data != null && data.events.isNotEmpty()) {
                    this.updateData(data.events)
                } else {
                    this.showErrorMessage("Event Kosong")
                }
                this.hideLoading()
            }
        }
    }
}