package com.example.unravel

import com.example.unravel.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class UnravelApp : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
        DaggerAppComponent
            .builder()
            .application(this)
            .build()


}