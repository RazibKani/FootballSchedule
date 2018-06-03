package com.razibkani.footballschedule

import android.app.Application
import android.content.Context
import com.razibkani.footballschedule.injection.component.ApplicationComponent
import com.razibkani.footballschedule.injection.component.DaggerApplicationComponent
import com.razibkani.footballschedule.injection.module.ApplicationModule

class FootballScheduleApp : Application() {

    private lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()

        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this)).build()
        applicationComponent.inject(this)
    }

    companion object {
        fun get(context: Context): FootballScheduleApp {
            return context.applicationContext as FootballScheduleApp
        }
    }

    fun getComponent(): ApplicationComponent {
        return applicationComponent
    }
}