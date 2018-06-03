package com.razibkani.footballschedule.ui.base

/**
 * Created by razibkani on 26/04/18.
 */
open class BasePresenter<T : MvpView> : Presenter<T> {

    var mvpView: T? = null

    override fun attachView(mvpView: T) {
        this.mvpView = mvpView
    }

    override fun detachView() {
        mvpView = null
    }
}