package com.shock.saturdaylifestyle.view.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.shock.saturdaylifestyle.utils.utility.Constants
import com.shock.saturdaylifestyle.view.login_register.LoginRegisterActivity
import com.shock.saturdaylifestyle.view.onboading.OnboardingActivity
import com.shock.saturdaylifestyle.utility.CommonUtilities
import saturdaylifestyle.R
import javax.inject.Inject

class SplashActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModel : SplashViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        splashHandler()
    }

    private fun splashHandler() {
        Handler().postDelayed({

            if (!CommonUtilities.getBoolean(this, Constants.IS_LOGIN)) {

                CommonUtilities.fireActivityIntent(
                    this,
                    Intent(this, OnboardingActivity::class.java),
                    isFinish = true,
                    isForward = true
                )

            } else {

                CommonUtilities.fireActivityIntent(
                    this,
                    Intent(this, LoginRegisterActivity::class.java),
                    isFinish = true,
                    isForward = true
                )

            }

        }, 2000)

    }
}