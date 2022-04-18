package com.shock.saturdaylifestyle.di

import com.shock.saturdaylifestyle.data.remote.repository.TravelRepository
import com.shock.saturdaylifestyle.data.remote.repository.TravelRepositoryImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class DataModule {

    @Singleton
    @Binds
    abstract fun provideRepository(travel: TravelRepositoryImpl) : TravelRepository
}