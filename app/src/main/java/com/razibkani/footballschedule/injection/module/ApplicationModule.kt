package com.razibkani.footballschedule.injection.module

import android.app.Application
import android.content.Context
import com.razibkani.footballschedule.data.DataManager
import com.razibkani.footballschedule.data.local.FootballScheduleDbHelper
import com.razibkani.footballschedule.data.remote.ApiService
import com.razibkani.footballschedule.injection.ApplicationContext
import com.razibkani.footballschedule.utils.CoroutineContextProvider
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: Application) {

    @Provides
    fun provideApplication(): Application = application

    @Provides
    @ApplicationContext
    fun provideContext(): Context = application

    @Provides
    @Singleton
    fun provideApiService(): ApiService = ApiService.create()

    @Provides
    @Singleton
    fun provideDbHelper(): FootballScheduleDbHelper = FootballScheduleDbHelper.getInstance(application)

    @Provides
    @Singleton
    fun provideDataManager(): DataManager = DataManager(provideApiService(), provideDbHelper())

    @Provides
    @Singleton
    fun coroutineContextProvider(): CoroutineContextProvider = CoroutineContextProvider()
}