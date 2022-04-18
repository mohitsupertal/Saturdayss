package com.shock.saturdaylifestyle.view.login_register

import android.app.DatePickerDialog
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.view.View
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintLayout
import dagger.android.AndroidInjection
import saturdaylifestyle.R
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class RegisterForm1Activity : AppCompatActivity(), DatePickerDialog.OnDateSetListener {

    @Inject
    lateinit var viewModel : RegisterViewModel

    private lateinit var tv_dob : TextView
    private lateinit var tv_alert_first_name : TextView
    private lateinit var tv_alert_last_name : TextView
    private lateinit var tv_alert_email : TextView
    private lateinit var tv_alert_dob : TextView
    private lateinit var cl_last_name : ConstraintLayout
    private lateinit var cl_first_name : ConstraintLayout
    private lateinit var cl_email : ConstraintLayout
    private lateinit var cl_dob : ConstraintLayout
    private lateinit var ed_first_name : EditText
    private lateinit var ed_last_name : EditText
    private lateinit var ed_email : EditText
    private lateinit var rg_gender : RadioGroup

    private var genderType : Int = -1

    private lateinit var btn_continue : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_form1)

        tv_alert_dob = findViewById(R.id.tv_alert_dob)
        tv_alert_email = findViewById(R.id.tv_alert_email)
        tv_alert_last_name = findViewById(R.id.tv_alert_last_name)
        tv_alert_first_name = findViewById(R.id.tv_alert_first_name)
        cl_dob = findViewById(R.id.cl_dob)
        cl_email = findViewById(R.id.cl_email)
        cl_last_name = findViewById(R.id.cl_last_name)
        cl_first_name = findViewById(R.id.cl_first_name)
        ed_first_name = findViewById(R.id.ed_first_name)
        ed_last_name = findViewById(R.id.ed_last_name)
        ed_email = findViewById(R.id.ed_email)
        btn_continue = findViewById(R.id.btn_continue)
        rg_gender = findViewById(R.id.rg_gender)
        tv_dob = findViewById(R.id.tv_dob)
        tv_dob.setOnClickListener {
            pickerClick()
        }

        btn_continue.setOnClickListener {

            continueClick()
        }

        initTextChangeListeners()

    }


    private fun initTextChangeListeners() {

        ed_first_name.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (s != null) {

                    if (!ed_first_name.text.toString().isNotEmpty())
                    {
                        cl_first_name.background = resources.getDrawable(R.drawable.bg_edittext_error)
                        tv_alert_first_name.visibility= View.VISIBLE
                    }


                    initFieldsObserver()
                }
            }

            override fun beforeTextChanged(
                s: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        ed_last_name.addTextChangedListener(object : TextWatcher {
            @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
            override fun afterTextChanged(s: Editable?) {
                if (s != null) {

                    if (!ed_last_name.text.toString().isNotEmpty())
                    {
                        cl_last_name.background = resources.getDrawable(R.drawable.bg_edittext_error)
                        tv_alert_last_name.visibility= View.VISIBLE
                    }

                    initFieldsObserver()

                }
            }

            override fun beforeTextChanged(
                s: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        ed_email.addTextChangedListener(object : TextWatcher {
            @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
            override fun afterTextChanged(s: Editable?) {
                if (s != null) {

                    if (!ed_email.text.toString().isNotEmpty())
                    {
                        cl_email.background = resources.getDrawable(R.drawable.bg_edittext_error)
                        tv_alert_email.visibility= View.VISIBLE
                    }

                    initFieldsObserver()

                }
            }

            override fun beforeTextChanged(
                s: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        tv_dob.addTextChangedListener(object : TextWatcher {
            @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
            override fun afterTextChanged(s: Editable?) {
                if (s != null) {

                    if (!tv_dob.text.toString().isNotEmpty())
                    {
                        cl_dob.background = resources.getDrawable(R.drawable.bg_edittext_error)
                        tv_alert_dob.visibility= View.VISIBLE
                    }

                    initFieldsObserver()

                }
            }

            override fun beforeTextChanged(
                s: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        rg_gender.setOnCheckedChangeListener { radioGroup, id ->

            when(id){

                R.id.rbFemale ->{

                    genderType = 0
                }

                R.id.rbMale ->{
                    genderType = 1
                }


            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun initFieldsObserver() {

        if (ed_first_name.text.toString().isNotEmpty())
        {
            cl_first_name.background = resources.getDrawable(R.drawable.bg_edittext)
            tv_alert_first_name.visibility= View.INVISIBLE
        }


        if (ed_last_name.text.toString().isNotEmpty())
        {
            cl_last_name.background = resources.getDrawable(R.drawable.bg_edittext)
            tv_alert_last_name.visibility= View.INVISIBLE
        }


        if (ed_email.text.toString().isNotEmpty())
        {
            cl_email.background = resources.getDrawable(R.drawable.bg_edittext)
            tv_alert_email.visibility= View.INVISIBLE
        }


        if (tv_dob.text.toString().isNotEmpty())
        {
            cl_dob.background = resources.getDrawable(R.drawable.bg_edittext)
            tv_alert_dob.visibility= View.INVISIBLE
        }


        if (ed_first_name.text.toString().isNotEmpty() &&
            ed_last_name.text.toString().isNotEmpty()  &&
            tv_dob.text.toString().isNotEmpty()  &&
            Patterns.EMAIL_ADDRESS.matcher(ed_email!!.text.toString()).matches()
        ) {

            btn_continue.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#133B64"));
            btn_continue.setTextColor(Color.parseColor("#FFFFFF"))
        }else
        {
            btn_continue.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#D8D8D8"))
            btn_continue.setTextColor(Color.parseColor("#979797"))


        }


    }

    private fun continueClick() {


        if ( ed_first_name.text.isEmpty()) {
             cl_first_name.background = resources.getDrawable(R.drawable.bg_edittext_error)
             tv_alert_first_name.visibility= View.VISIBLE

        } else if ( ed_last_name.text.isEmpty()) {
             cl_last_name.background = resources.getDrawable(R.drawable.bg_edittext_error)
             tv_alert_last_name.visibility= View.VISIBLE
        }  else if ( ed_email.text.isEmpty()) {
             cl_email.background = resources.getDrawable(R.drawable.bg_edittext_error)
             tv_alert_email.visibility= View.VISIBLE
        } else if (!(Patterns.EMAIL_ADDRESS.matcher(ed_email.text.toString())
                .matches()))
        {
            cl_email.background = resources.getDrawable(R.drawable.bg_edittext_error)
            tv_alert_email.visibility= View.VISIBLE
        } else if (tv_dob.text.isEmpty()) {

            cl_dob.background = resources.getDrawable(R.drawable.bg_edittext_error)
             tv_alert_dob.visibility= View.VISIBLE

        } else{

            viewModel.register(ed_first_name.text.toString() + " " + ed_last_name.text.toString(),
                LoginRegisterActivity.phone_number, LoginRegisterActivity.country_code, genderType, ""
            )
        }


    }

    private fun pickerClick() {

        val calendar = Calendar.getInstance()
        val mYear = calendar.get(Calendar.YEAR)
        val mMonth = calendar.get(Calendar.MONTH)
        val mDay = calendar.get(Calendar.DAY_OF_MONTH)
        var datePickerDialog = DatePickerDialog(this, this, mYear, mMonth, mDay)
        datePickerDialog.datePicker.maxDate = calendar.timeInMillis
        datePickerDialog.show()



    }


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        val calendar2: Calendar = Calendar.getInstance()
        calendar2.set(year, month, dayOfMonth, 0, 0, 0)
        val selectedDate = calendar2.time
        val dateFormat = SimpleDateFormat("dd-MMM-yyyy")
        var date= dateFormat.format(selectedDate)

        tv_dob.text = date.toString()

    }

}