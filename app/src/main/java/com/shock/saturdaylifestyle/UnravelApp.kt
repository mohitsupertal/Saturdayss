package com.shock.saturdaylifestyle

import android.content.Context
import com.google.firebase.FirebaseApp
import com.shock.saturdaylifestyle.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class UnravelApp : DaggerApplication() {
    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
    }
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
        DaggerAppComponent
            .builder()
            .application(this)
            .build()


}