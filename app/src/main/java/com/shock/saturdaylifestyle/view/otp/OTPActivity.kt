package com.shock.saturdaylifestyle.view.otp
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.chaos.view.PinView
import com.shock.saturdaylifestyle.view.login_register.RegisterForm1Activity
import com.shock.saturdaylifestyle.utility.CommonUtilities
import dagger.android.AndroidInjection
import saturdaylifestyle.R
import javax.inject.Inject


class OTPActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModel : OTPViewModel

    private val TAG = OTPActivity::class.java.simpleName

    var otp =""
    var actualOtp ="12345"

    private lateinit var tv_title : TextView
    private lateinit var tv_timer : TextView
    private lateinit var tv_try_again : TextView
    private lateinit var tv_wrong_number : TextView
    private lateinit var pv_pin : PinView

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_otp)

        tv_title = findViewById<TextView>(R.id.tv_title)
        tv_timer = findViewById<TextView>(R.id.tv_timer)
        tv_try_again = findViewById<TextView>(R.id.tv_try_again)
        tv_wrong_number = findViewById<TextView>(R.id.tv_wrong_number)
        pv_pin = findViewById<PinView>(R.id.pv_pin)
        initOtpViewListener()


        initTimer()

        tv_try_again.setOnClickListener {
            initTimer()
        }

        setObserver()

    }


    private fun setObserver(){

        viewModel.responseLiveData.observe(this){
            navigateToOTPScreen()
        }
    }

    private fun navigateToOTPScreen(){

        CommonUtilities.fireActivityIntent(
            this,
            Intent(this, RegisterForm1Activity::class.java),
            isFinish = false,
            isForward = true
        )
    }

    private fun initTimer() {

            tv_timer.visibility=View.VISIBLE
          tv_try_again.visibility=View.GONE

        val timer = object: CountDownTimer(30000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                    tv_timer.text ="Try again in "+ millisUntilFinished / 1000

            }

            override fun onFinish() {
                    tv_timer.visibility=View.GONE
                  tv_try_again.visibility=View.VISIBLE

            }
        }
        timer.start()    }


    private fun initOtpViewListener() {


          pv_pin.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

                validateOTP()

            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }


    fun validateOTP()
    {

        otp =   pv_pin.text.toString()

        if (otp.length==5)
        {

            if (otp == actualOtp)
            {
                //CommonUtilities.showToast(this,"success")
                  pv_pin.setTextColor(Color.parseColor("#3A6A67"))

            }else
            {
                  pv_pin.setTextColor(Color.parseColor("#2A2F32"))
                  tv_wrong_number.visibility=View.VISIBLE
                  pv_pin.setItemBackground(resources.getDrawable(R.drawable.bg_pin_error));

            }

        }else
        {
              pv_pin.setTextColor(Color.parseColor("#2A2F32"))
              tv_wrong_number.visibility=View.INVISIBLE
              pv_pin.setItemBackground(resources.getDrawable(R.drawable.bg_pin_default));


        }
    }

}