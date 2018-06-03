package com.razibkani.footballschedule.ui.favoritesmatch

import com.razibkani.footballschedule.data.model.FavoriteEvent
import com.razibkani.footballschedule.ui.base.MvpView

interface FavoritesMatchMvpView : MvpView {

    fun showLoading()

    fun hideLoading()

    fun updateData(favoriteEvents: List<FavoriteEvent>)

}