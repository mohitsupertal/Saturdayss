package com.example.unravel.di

import com.example.unravel.data.remote.repository.TravelRepository
import com.example.unravel.data.remote.repository.TravelRepositoryImpl
import com.example.unravel.data.remote.source.DataService
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
abstract class DataModule {

    @Singleton
    @Binds
    abstract fun provideRepository(travel: TravelRepositoryImpl) : TravelRepository
}