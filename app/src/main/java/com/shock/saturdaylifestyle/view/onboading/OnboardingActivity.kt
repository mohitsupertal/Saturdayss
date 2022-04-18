package com.shock.saturdaylifestyle.view.onboading

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import androidx.viewpager.widget.ViewPager
import com.shock.saturdaylifestyle.view.login_register.LoginRegisterActivity
import com.shock.saturdaylifestyle.view.onboading.adapter.OnboardingPagerAdapter
import com.saturdays.on_boarding.model.ViewPagerDM
import com.shock.saturdaylifestyle.utility.CommonUtilities
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator
import dagger.android.AndroidInjection
import saturdaylifestyle.R

class OnboardingActivity : AppCompatActivity() {

    var pager_itemList = ArrayList<ViewPagerDM>()
    private lateinit var btn_login_create_account : Button
    private lateinit var tv_skip : TextView
    private lateinit var viewpager : ViewPager
    private lateinit var dots_indicator : DotsIndicator

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        supportActionBar?.hide()


        tv_skip = findViewById(R.id.tv_skip)
        dots_indicator = findViewById(R.id.dots_indicator)
        viewpager = findViewById(R.id.viewpager)
        btn_login_create_account = findViewById(R.id.btn_login_create_account)
        btn_login_create_account.setOnClickListener {
            onLoginBtnClick()
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            val w: Window = window
            w.setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
            )
        }

        /**
         *
         *   going to onboarding screen from splash
         *
         * */
        pager_itemList.clear()
        pager_itemList.add(
            ViewPagerDM(
                R.mipmap.iv_onboarding1,
                "ANYWHERE IN THE WORLD!",
                "Enjoy free shipping all across the globe\nonly for you!"
            )
        )
        pager_itemList.add(
            ViewPagerDM(
                R.mipmap.iv_onboarding2,
                "PRICE INCLUDES\nPRESCRIPTION LENSES",
                "Starting from Rp1.295k, we promise there\nwon’t be any hidden cost! Unless you want\nto upgrade your lenses!"
            )
        )
        pager_itemList.add(
            ViewPagerDM(
                R.mipmap.iv_onboarding2,
                "HOME TRY ON",
                "Try our entire collection (100+ frames) and\neye exam all for FREE in the comfort of\nyour home!"
            )
        )
        pager_itemList.add(
            ViewPagerDM(
                R.mipmap.iv_onboarding2,
                "SAVE MORE BUCKS!",
                "Enjoy our exclusive promos as well as our\nloyalty program to save some more!"
            )
        )
        pager_itemList.add(
            ViewPagerDM(
                R.mipmap.iv_onboarding5,
                "OFFLINE STORES",
                "Still not sure about how it looks on you?\nTry it on at our offline stores! We are\navailable all across Indonesia."
            )
        )


        val adapter = OnboardingPagerAdapter(this, pager_itemList)
        viewpager!!.adapter = adapter
        dots_indicator!!.setViewPager(viewpager!!)
        viewpager!!.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {

                btn_login_create_account!!.setOnClickListener {

                    CommonUtilities.fireActivityIntent(
                        this@OnboardingActivity,
                        Intent(this@OnboardingActivity, LoginRegisterActivity::class.java),
                        isFinish = true,
                        isForward = true
                    )

                }

            }

        })


        tv_skip!!.setOnClickListener {

            CommonUtilities.fireActivityIntent(
                this@OnboardingActivity,
                Intent(this@OnboardingActivity, LoginRegisterActivity::class.java),
                isFinish = true,
                isForward = true
            )


        }
    }

    private fun onLoginBtnClick() {
        startActivity(Intent(this, LoginRegisterActivity::class.java))
    }
}