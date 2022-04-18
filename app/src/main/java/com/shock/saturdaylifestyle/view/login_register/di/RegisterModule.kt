package com.shock.saturdaylifestyle.view.login_register.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.shock.saturdaylifestyle.data.remote.repository.TravelRepository
import com.shock.saturdaylifestyle.view.login_register.RegisterForm1Activity
import com.shock.saturdaylifestyle.view.login_register.RegisterViewModel
import dagger.Module
import dagger.Provides
import javax.inject.Inject
import javax.inject.Singleton

@Module
object RegisterModule {

    @Provides
    fun providesRegisterVMFactory(travelService: TravelRepository) : RegisterViewModelFactory = RegisterViewModelFactory(travelService)

    @Provides
    fun provideRegisterViewModel(activity: RegisterForm1Activity, factory: RegisterViewModelFactory) : RegisterViewModel =
        ViewModelProvider(activity, factory).get(RegisterViewModel::class.java)
}

@Singleton
class RegisterViewModelFactory @Inject constructor(private val travelService: TravelRepository) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return RegisterViewModel(travelService) as T
    }
}