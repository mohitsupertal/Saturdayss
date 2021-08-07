package com.example.unravel.di

import com.example.unravel.UnravelApp
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ActivityModule::class,
        DataModule::class,
        NetworkModule::class
    ]
)
interface AppComponent : AndroidInjector<UnravelApp> {

    @Component.Builder
    interface Builder{

        @BindsInstance
        fun application(app: UnravelApp) : Builder

        fun build() : AppComponent
    }
}