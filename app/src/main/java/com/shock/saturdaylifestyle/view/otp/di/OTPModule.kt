package com.shock.saturdaylifestyle.view.otp.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.shock.saturdaylifestyle.data.remote.repository.TravelRepository
import com.shock.saturdaylifestyle.view.otp.OTPActivity
import com.shock.saturdaylifestyle.view.otp.OTPViewModel
import dagger.Module
import dagger.Provides
import javax.inject.Inject
import javax.inject.Singleton

@Module
class OTPModule {
    @Provides
    fun providesLoginVMFactory(travelService: TravelRepository) : OTPViewModelFactory = OTPViewModelFactory(travelService)

    @Provides
    fun provideLoginViewModel(activity: OTPActivity, factory: OTPViewModelFactory) : OTPViewModel =
        ViewModelProvider(activity, factory).get(OTPViewModel::class.java)
}

@Singleton
class OTPViewModelFactory @Inject constructor(private val travelService: TravelRepository) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return OTPViewModel(travelService) as T
    }
}