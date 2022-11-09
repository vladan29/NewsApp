package com.vladan.newsapp

import android.app.Application
import com.vladan.networkchecker.InternetManager
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class NewsApp: Application() {
    private lateinit var internetManager: InternetManager

    override fun onCreate() {
        super.onCreate()
        internetManager = InternetManager.getInternetManager(this)
        internetManager.registerInternetMonitor()
    }
}