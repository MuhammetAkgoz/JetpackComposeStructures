package com.example.jetpackcomposelesson

import android.app.Application


class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        println("Your application has been created")
    }
}