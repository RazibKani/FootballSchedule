package com.razibkani.footballschedule.data

import android.database.sqlite.SQLiteConstraintException
import com.razibkani.footballschedule.data.local.DbCallback
import com.razibkani.footballschedule.data.local.FootballScheduleDbHelper
import com.razibkani.footballschedule.data.model.*
import com.razibkani.footballschedule.data.remote.ApiService
import com.razibkani.footballschedule.utils.Constants
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select

class DataManager(private val apiService: ApiService,
                  private val database: FootballScheduleDbHelper) {

    suspend fun getFootballPrevEvent(): FootballEventResponse {
        return apiService.getFootballPrevEvent(Constants.LEAGUEID).await()
    }

    suspend fun getFootballNextEvent(): FootballEventResponse {
        return apiService.getFootballNextEvent(Constants.LEAGUEID).await()
    }

    suspend fun getFootballDetailEvent(idEvent: String): FootballDetailEventResponse {
        return apiService.getFootballDetailEvent(idEvent).await()
    }

    suspend fun getFootballClubDetail(clubName: String): FootballClubResponse {
        return apiService.getFootballClubDetail(clubName).await()
    }

    fun addToFavorite(event: Event, callback: DbCallback) {
        try {
            database.use {
                insert(FavoriteEvent.TABLE_FAVORITE,
                        FavoriteEvent.ID_EVENT to event.idEvent,
                        FavoriteEvent.DATE_EVENT to event.dateEvent,
                        FavoriteEvent.HOME_TEAM_EVENT to event.strHomeTeam,
                        FavoriteEvent.AWAY_TEAM_EVENT to event.strAwayTeam,
                        FavoriteEvent.HOME_SCORE_EVENT to event.intHomeScore,
                        FavoriteEvent.AWAY_SCORE_EVENT to event.intAwayScore)

            }
            callback.onSuccess()
        } catch (e: SQLiteConstraintException) {
            callback.onFailed()
        }
    }

    fun removeFromFavorite(eventId: String, callback: DbCallback) {
        try {
            database.use {
                delete(FavoriteEvent.TABLE_FAVORITE, "(EVENT_ID = {id})",
                        "id" to eventId)
            }
            callback.onSuccess()
        } catch (e: SQLiteConstraintException) {
            callback.onFailed()
        }
    }

    fun getFavoritesEvent(): List<FavoriteEvent> {
        var favorites: List<FavoriteEvent> = ArrayList()
        try {
            database.use {
                val result = select(FavoriteEvent.TABLE_FAVORITE)
                favorites = result.parseList(classParser())
            }
        } catch (e: SQLiteConstraintException) {
            favorites = ArrayList()
        }
        return favorites
    }

    fun favoriteState(eventId: String): Boolean {
        var isFavorite = false
        try {
            database.use {
                val result = select(FavoriteEvent.TABLE_FAVORITE)
                        .whereArgs("(EVENT_ID = {id})",
                                "id" to eventId)
                val favorite = result.parseList(classParser<FavoriteEvent>())
                if (!favorite.isEmpty()) isFavorite = true
            }
        } catch (e: SQLiteConstraintException) {
            isFavorite = false
        }
        return isFavorite
    }
}