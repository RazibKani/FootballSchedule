package com.razibkani.footballschedule.ui.base

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import com.razibkani.footballschedule.FootballScheduleApp
import com.razibkani.footballschedule.injection.component.ActivityComponent
import com.razibkani.footballschedule.injection.component.DaggerActivityComponent

/**
 * Created by razibkani on 26/04/18.
 */
@SuppressLint("Registered")
open class BaseActivity : AppCompatActivity() {

    private var activityComponent: ActivityComponent? = null

    fun activityComponent(): ActivityComponent? {
        if (activityComponent == null) {
            activityComponent = DaggerActivityComponent.builder()
                    .applicationComponent(FootballScheduleApp.get(this).getComponent())
                    .build()
        }
        return activityComponent
    }
}