package com.razibkani.footballschedule.ui.detailmatch

import com.razibkani.footballschedule.data.model.DetailEvent
import com.razibkani.footballschedule.data.model.Team
import com.razibkani.footballschedule.ui.base.MvpView

interface MatchDetailMvpView : MvpView {

    fun showLoading()

    fun hideLoading()

    fun showSuccessMessage(successMessage: String)

    fun showErrorMessage(errorMessage: String)

    fun showData(event: DetailEvent)

    fun showHomeTeamImage(team: Team)

    fun showAwayTeamImage(team: Team)
}