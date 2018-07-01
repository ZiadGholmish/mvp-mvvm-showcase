package com.mystride.dagger

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(application: Application) {

    private var application: Application = application

    @Singleton
    @Provides
    fun provideApplication(): Context {
        return application
    }

    @Singleton
    @Provides
    fun provideShared(): SharedPreferences {
        return application.getSharedPreferences("my_stride_shared", 0)
    }

    @Singleton
    @Provides
    fun provideGson(): Gson {
        return Gson()
    }
}

