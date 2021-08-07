package com.example.unravel.di

import com.example.unravel.view.MainActivity
import com.example.unravel.view.di.MainModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector(modules = [MainModule::class])
    abstract fun provideMainActivity() : MainActivity
}