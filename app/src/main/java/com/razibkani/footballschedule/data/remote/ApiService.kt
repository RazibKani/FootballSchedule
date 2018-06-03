package com.razibkani.footballschedule.data.remote

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.experimental.CoroutineCallAdapterFactory
import com.razibkani.footballschedule.data.model.FootballClubResponse
import com.razibkani.footballschedule.data.model.FootballDetailEventResponse
import com.razibkani.footballschedule.data.model.FootballEventResponse
import kotlinx.coroutines.experimental.Deferred
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("eventspastleague.php")
    fun getFootballPrevEvent(@Query("id") part: String): Deferred<FootballEventResponse>

    @GET("eventsnextleague.php")
    fun getFootballNextEvent(@Query("id") part: String): Deferred<FootballEventResponse>

    @GET("lookupevent.php")
    fun getFootballDetailEvent(@Query("id") part: String): Deferred<FootballDetailEventResponse>

    @GET("searchteams.php")
    fun getFootballClubDetail(@Query("t") clubName: String): Deferred<FootballClubResponse>

    companion object Factory {
        fun create(): ApiService {
            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(CoroutineCallAdapterFactory())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl("https://www.thesportsdb.com/api/v1/json/1/")
                    .build()

            return retrofit.create(ApiService::class.java)
        }
    }
}