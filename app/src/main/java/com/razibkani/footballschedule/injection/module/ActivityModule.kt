package com.razibkani.footballschedule.injection.module

import android.app.Activity
import android.content.Context
import dagger.Module
import dagger.Provides
import com.razibkani.footballschedule.injection.ActivityContext

@Module
class ActivityModule(private val activity: Activity) {

    @Provides
    fun provideActivity(): Activity {
        return activity
    }

    @Provides
    @ActivityContext
    fun providesContext(): Context {
        return activity
    }
}