package com.diegovr17.miu_app_roomdatabase

import android.app.Application
import android.content.Context

class ContextNotas : Application() {

    companion object{
        lateinit var context: Context
    }
    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }
}