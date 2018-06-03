package com.razibkani.footballschedule.ui.favoritesmatch

import com.razibkani.footballschedule.data.DataManager
import com.razibkani.footballschedule.ui.base.BasePresenter
import javax.inject.Inject

class FavoritesMatchPresenter @Inject constructor(private val dataManager: DataManager) : BasePresenter<FavoritesMatchMvpView>() {

    fun getFavoriteEvents() {
        mvpView?.apply {
            showLoading()
            updateData(dataManager.getFavoritesEvent())
            hideLoading()
        }
    }
}