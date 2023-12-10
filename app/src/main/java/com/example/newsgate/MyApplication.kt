package com.example.newsgate

import android.app.Application
import com.example.newsgate.news.di.diModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MyApplication)
            modules(diModule)
        }
    }
}
