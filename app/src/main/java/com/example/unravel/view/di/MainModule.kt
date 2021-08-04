package com.example.unravel.view.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.unravel.view.MainActivity
import com.example.unravel.view.MainViewModel
import dagger.Module
import dagger.Provides
import javax.inject.Inject
import javax.inject.Singleton

@Module
object MainModule {

    @Provides
    fun providesMainVMFactory() : MainViewModelFactory = MainViewModelFactory()

    @Provides
    fun provideMainViewModel(activity: MainActivity, factory: MainViewModelFactory) : MainViewModel =
        ViewModelProvider(activity, factory).get(MainViewModel::class.java)
}

@Singleton
class MainViewModelFactory @Inject constructor() : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel() as T
    }
}