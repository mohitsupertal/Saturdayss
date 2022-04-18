package com.shock.saturdaylifestyle.di

import com.shock.saturdaylifestyle.view.login_register.LoginRegisterActivity
import com.shock.saturdaylifestyle.view.login_register.RegisterForm1Activity
import com.shock.saturdaylifestyle.view.login_register.di.LoginModule
import com.shock.saturdaylifestyle.view.login_register.di.RegisterModule
import com.shock.saturdaylifestyle.view.main.MainActivity
import com.shock.saturdaylifestyle.view.main.di.MainModule
import com.shock.saturdaylifestyle.view.onboading.OnboardingActivity
import com.shock.saturdaylifestyle.view.onboading.OnboardingModule
import com.shock.saturdaylifestyle.view.otp.OTPActivity
import com.shock.saturdaylifestyle.view.otp.di.OTPModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector(modules = [MainModule::class])
    abstract fun provideMainActivity() : MainActivity

    @ContributesAndroidInjector(modules = [LoginModule::class])
    abstract fun provideLoginActivity() : LoginRegisterActivity

    @ContributesAndroidInjector(modules = [RegisterModule::class])
    abstract fun provideRegisterActivity() : RegisterForm1Activity

    @ContributesAndroidInjector(modules = [OnboardingModule::class])
    abstract fun provideOnBoardingActivity() : OnboardingActivity

    @ContributesAndroidInjector(modules = [OTPModule::class])
    abstract fun provideOTPActivity() : OTPActivity
}