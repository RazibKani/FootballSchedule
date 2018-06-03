package com.razibkani.footballschedule.ui.detailmatch

import com.razibkani.footballschedule.data.DataManager
import com.razibkani.footballschedule.data.local.DbCallback
import com.razibkani.footballschedule.data.model.Event
import com.razibkani.footballschedule.ui.base.BasePresenter
import com.razibkani.footballschedule.utils.CoroutineContextProvider
import kotlinx.coroutines.experimental.async
import javax.inject.Inject

class MatchDetailPresenter @Inject constructor(private val dataManager: DataManager,
                                               private val context: CoroutineContextProvider) : BasePresenter<MatchDetailMvpView>() {

    fun getMatchDetail(eventId: String) {
        mvpView?.showLoading()
        async(context.main) {
            val data = dataManager.getFootballDetailEvent(eventId)
            mvpView?.apply {
                val event = data.events[0]
                this.showData(event)
                this.hideLoading()
            }
        }
    }

    fun getFootballClubDetail(clubName: String, isHome: Boolean) {
        mvpView?.showLoading()
        async(context.main) {
            val data = dataManager.getFootballClubDetail(clubName)

            mvpView?.apply {
                val team = data.teams[0]
                if (isHome) {
                    showHomeTeamImage(team)
                } else {
                    showAwayTeamImage(team)
                }
            }
        }
    }

    fun favoriteState(eventId: String): Boolean = dataManager.favoriteState(eventId)

    fun removeFromFavorite(eventId: String) {
        dataManager.removeFromFavorite(eventId, object : DbCallback {
            override fun onSuccess() {
                mvpView?.showSuccessMessage("Removed from Favorite")
            }

            override fun onFailed() {
                mvpView?.showErrorMessage("Failed Remove from Favorite")
            }
        })
    }

    fun addToFavorite(event: Event) {
        dataManager.addToFavorite(event, object : DbCallback {
            override fun onSuccess() {
                mvpView?.showSuccessMessage("Added to Favorite")
            }

            override fun onFailed() {
                mvpView?.showErrorMessage("Failed Add to Favorite")
            }
        })
    }
}