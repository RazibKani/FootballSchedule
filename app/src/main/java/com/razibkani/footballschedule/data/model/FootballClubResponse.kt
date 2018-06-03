package com.razibkani.footballschedule.data.model
import com.google.gson.annotations.SerializedName

data class FootballClubResponse(
    @SerializedName("teams") val teams: List<Team>
)

data class Team(
    @SerializedName("idTeam") val idTeam: String,
    @SerializedName("strTeamBadge") val teamBadge: String
)