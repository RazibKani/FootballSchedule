package com.razibkani.footballschedule.injection.component

import android.content.Context
import com.razibkani.footballschedule.FootballScheduleApp
import com.razibkani.footballschedule.data.DataManager
import com.razibkani.footballschedule.injection.ApplicationContext
import com.razibkani.footballschedule.injection.module.ApplicationModule
import com.razibkani.footballschedule.utils.CoroutineContextProvider
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

    fun inject(application: FootballScheduleApp)

    @ApplicationContext
    fun context(): Context

    fun dataManager(): DataManager
    fun coroutineContextProvider(): CoroutineContextProvider
}