package com.example.composebasics

import android.app.Application
import com.example.composebasics.di.app_module
import com.example.composebasics.di.single_module
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(app_module + single_module)
            androidContext(this@MyApplication)
        }
    }
}