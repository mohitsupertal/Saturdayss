package com.shock.saturdaylifestyle.view.main.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.shock.saturdaylifestyle.data.remote.repository.TravelRepository
import com.shock.saturdaylifestyle.view.main.MainActivity
import com.shock.saturdaylifestyle.view.main.MainViewModel
import dagger.Module
import dagger.Provides
import javax.inject.Inject
import javax.inject.Singleton

@Module
object MainModule {

    @Provides
    fun providesMainVMFactory(travelService: TravelRepository) : MainViewModelFactory = MainViewModelFactory(travelService)

    @Provides
    fun provideMainViewModel(activity: MainActivity, factory: MainViewModelFactory) : MainViewModel =
        ViewModelProvider(activity, factory).get(MainViewModel::class.java)
}

@Singleton
class MainViewModelFactory @Inject constructor(private val travelService: TravelRepository) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(travelService) as T
    }
}