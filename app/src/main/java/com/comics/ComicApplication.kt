package com.comics

import android.app.Application
import com.comics.di.AppModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class ComicApplication : Application() {

    override fun onCreate() {
        super.onCreate()
       startKoin{
            androidContext(this@ComicApplication)
            modules(AppModule.list)
       }
    }
}