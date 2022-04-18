package com.shock.saturdaylifestyle.view.login_register.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.shock.saturdaylifestyle.data.remote.repository.TravelRepository
import com.shock.saturdaylifestyle.view.login_register.LoginRegisterActivity
import com.shock.saturdaylifestyle.view.login_register.LoginViewModel
import dagger.Module
import dagger.Provides
import javax.inject.Inject
import javax.inject.Singleton

@Module
object LoginModule {

    @Provides
    fun providesLoginVMFactory(travelService: TravelRepository) : LoginViewModelFactory = LoginViewModelFactory(travelService)

    @Provides
    fun provideLoginViewModel(activity: LoginRegisterActivity, factory: LoginViewModelFactory) : LoginViewModel =
        ViewModelProvider(activity, factory).get(LoginViewModel::class.java)
}

@Singleton
class LoginViewModelFactory @Inject constructor(private val travelService: TravelRepository) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return LoginViewModel(travelService) as T
    }
}