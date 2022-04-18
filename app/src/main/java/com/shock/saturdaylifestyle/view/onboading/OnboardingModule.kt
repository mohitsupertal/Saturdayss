package com.shock.saturdaylifestyle.view.onboading

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.shock.saturdaylifestyle.data.remote.repository.TravelRepository
import com.shock.saturdaylifestyle.view.main.MainActivity
import dagger.Module
import dagger.Provides
import javax.inject.Inject
import javax.inject.Singleton

@Module
object OnboardingModule {

    @Provides
    fun providesOnboardingVMFactory(travelService: TravelRepository) : OnboardingViewModelFactory = OnboardingViewModelFactory(travelService)

    @Provides
    fun provideOnboardingViewModel(activity: MainActivity, factory: OnboardingViewModelFactory) : OnboardingViewModel =
        ViewModelProvider(activity, factory).get(OnboardingViewModel::class.java)
}

@Singleton
class OnboardingViewModelFactory @Inject constructor(private val travelService: TravelRepository) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return OnboardingViewModel(travelService) as T
    }
}