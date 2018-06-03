package com.razibkani.footballschedule.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FavoriteEvent(
        val id: Long?,
        val idEvent: String?,
        val dateEvent: String?,
        val homeTeam: String?,
        val awayTeam: String?,
        val homeScore: Int?,
        val awayScore: Int?) : Parcelable {

    companion object {
        const val TABLE_FAVORITE: String = "TABLE_FAVORITE"
        const val ID: String = "ID_"
        const val ID_EVENT: String = "EVENT_ID"
        const val DATE_EVENT: String = "EVENT_DATE"
        const val HOME_TEAM_EVENT: String = "EVENT_HOME_TEAM"
        const val AWAY_TEAM_EVENT: String = "EVENT_AWAY_TEAM"
        const val HOME_SCORE_EVENT: String = "EVENT_HOME_SCORE"
        const val AWAY_SCORE_EVENT: String = "EVENT_AWAY_SCORE"
    }
}