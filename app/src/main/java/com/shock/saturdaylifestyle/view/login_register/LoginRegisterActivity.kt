package com.shock.saturdaylifestyle.view.login_register

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shock.saturdaylifestyle.utils.utility.MyBottomSheetDialog
import com.shock.saturdaylifestyle.view.login_register.models.CountryDM
import com.shock.saturdaylifestyle.view.otp.OTPActivity
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.auth.api.signin.GoogleSignInResult
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.saturdays.login_register.adapters.CountryAdapter
import com.shock.saturdaylifestyle.utility.CommonUtilities
import dagger.android.AndroidInjection
import saturdaylifestyle.R
import javax.inject.Inject

class LoginRegisterActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModel : LoginViewModel

    private var countriesList: ArrayList<CountryDM> = ArrayList()

    var countryAdapter : CountryAdapter? = null

    private lateinit var bottomSheetDialog : MyBottomSheetDialog
    private lateinit var tv_phone_code : TextView
    private lateinit var btn_contibute : TextView
    private lateinit var ed_phone_no : EditText
    private lateinit var rl_google : LinearLayout

    /**
     *  below code for google signin
     */
    private var mfirebaseAuth: FirebaseAuth? = null
    private var mGoogleSignInClient: GoogleSignInClient? = null
    private var RC_SIGN_IN_GOOGLE_SIGNIN = 200

    companion object{
        var country_code : String = ""
        var phone_number : String = ""
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_register)

        btn_contibute = findViewById(R.id.btn_contibute)
        tv_phone_code = findViewById(R.id.tv_phone_code)
        ed_phone_no = findViewById(R.id.ed_phone_no)
        rl_google = findViewById(R.id.rl_google)
        tv_phone_code.setOnClickListener {
            pickerClick()
        }
        btn_contibute.setOnClickListener {
            continueClick()
        }

        rl_google.setOnClickListener {
            initGoogleIntegration()
        }
        setObserver()
    }

    private fun setObserver(){

        viewModel.responseLiveData.observe(this){
            navigateToOTPScreen()
        }
    }

    private fun pickerClick() {
        dialogCountryPicker(this)
    }

    fun setSelectedCountry(code:String)
    {
        tv_phone_code.text = code
    }

    private fun continueClick() {

        showBottomSheetWhatsapp()

    }

    private fun navigateToOTPScreen(){
        bottomSheetDialog.dismiss()

        CommonUtilities.fireActivityIntent(
            this,
            Intent(this, OTPActivity::class.java),
            isFinish = false,
            isForward = true
        )
    }

    private fun showBottomSheetWhatsapp() {
        country_code = tv_phone_code.text.toString()
        phone_number = ed_phone_no.text.toString()

        Log.d("*** LoginRegister >>>", ""+ phone_number)

        bottomSheetDialog = MyBottomSheetDialog(this, false)



        @SuppressLint("InflateParams") val view: View =
            layoutInflater.inflate(R.layout.dialog_send_otp_via_whatsapp, null)



        (view.findViewById(R.id.btn_continue) as View).setOnClickListener {

           viewModel.otpSend(phone_number, country_code)

        }


        (view.findViewById(R.id.iv_back) as View).setOnClickListener {

            bottomSheetDialog.dismiss()
        }


        (view.findViewById(R.id.tv_other_method) as View).setOnClickListener {

            bottomSheetDialog.dismiss()
            showBottomSheetOtherMethod()
        }



        bottomSheetDialog.setContentView(view)
        bottomSheetDialog.show()
    }

    private fun dialogCountryPicker(activity: Activity) {


        var dialogGet = CommonUtilities.customDialog(
            context = activity!!,
            dialogView = R.layout.dialog_country_picker
        )


        var rvCountry = dialogGet.findViewById<RecyclerView>(R.id.rvCountry)
        var edSearch = dialogGet.findViewById<EditText>(R.id.ed_search)


        countryAdapter =
            CountryAdapter(this,countriesList,dialogGet)
        rvCountry.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = countryAdapter
        }

        initCountrySearchListener(edSearch)

        dialogGet.show()
        dialogGet.setCancelable(true)

    }

    private fun initCountrySearchListener(edSearch: EditText) {

        edSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (s != null) {

                    if (s.isNotEmpty()) {

                        performCountrySearch(edSearch.text.toString().toLowerCase())

                    } else {

                        //    binding!!.edSearch.clearFocus()
                        val `in`: InputMethodManager =
                            getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                        `in`.hideSoftInputFromWindow(edSearch.windowToken, 0)

                        countryAdapter!!.updateList(countriesList)

                    }
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



    }


    fun performCountrySearch(s: String)
    {

        var countriesListResult: ArrayList<CountryDM> = ArrayList()


        for (i in countriesList)
        {
            if (i.code.toString().toLowerCase().contains(s) ||  i.countryName.toString().toLowerCase().contains(s) )
            {
                countriesListResult.add(i)
            }
        }

        countryAdapter!!.updateList(countriesListResult)

    }

    private fun showBottomSheetOtherMethod() {


        var bottomSheetDialog = MyBottomSheetDialog(this, false)


        @SuppressLint("InflateParams") val view: View =
            layoutInflater.inflate(R.layout.dialog_choose_verification_method, null)


        (view.findViewById(R.id.iv_back) as View).setOnClickListener {

            bottomSheetDialog.dismiss()
            showBottomSheetWhatsapp()
        }



        (view.findViewById(R.id.rl_whatsapp) as View).setOnClickListener {

            bottomSheetDialog.dismiss()
        }


        (view.findViewById(R.id.rl_misscall) as View).setOnClickListener {

            bottomSheetDialog.dismiss()
        }



        bottomSheetDialog.setContentView(view)
        bottomSheetDialog.show()
    }

    /**
     *  below code for google signin
     */
    private fun initGoogleIntegration() {
        mfirebaseAuth = FirebaseAuth.getInstance()

        // Configure Google Sign In
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(com.firebase.ui.auth.R.string.default_web_client_id))
            .requestEmail()
            .build()

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        val intent = mGoogleSignInClient!!.signInIntent
        startActivityForResult(intent, RC_SIGN_IN_GOOGLE_SIGNIN)


    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN_GOOGLE_SIGNIN) {
            val result = data?.let { Auth.GoogleSignInApi.getSignInResultFromIntent(it) }
            handleGoogleSignInResult(result!!)
        }
    }


    private fun handleGoogleSignInResult(result: GoogleSignInResult) {
        if (result.isSuccess) {
            val account = result.signInAccount
            val credential = GoogleAuthProvider.getCredential(account!!.idToken, null)
            firebaseAuthWithGoogle(credential)
        } else {
            // Google Sign In failed, update UI appropriately
            Toast.makeText(this, "Google Login Unsuccessful" + result.status.statusCode, Toast.LENGTH_LONG).show();
        }
    }


    private fun firebaseAuthWithGoogle(credential: AuthCredential) {
        mfirebaseAuth?.signInWithCredential(credential)?.addOnCompleteListener(
            this,
            OnCompleteListener<AuthResult?> { task ->
                Log.d("TAG", "signInWithCredential:onComplete:" + task.isSuccessful)
                if (task.isSuccessful) {
                    //  Toast.makeText(this, "google signin successful", Toast.LENGTH_LONG).show();
                    //  mGoogleSignInClient!!.signOut()



                    CommonUtilities.fireActivityIntent(
                        this,
                        Intent(this, RegisterForm1Activity::class.java),
                        isFinish = false,
                        isForward = true
                    )



                } else {
                    Log.w("TAG", "signInWithCredential" + task.exception!!.message)
                    task.exception!!.printStackTrace()
                    Toast.makeText(this, " Google Authentication failed.", Toast.LENGTH_SHORT).show()
                }
            })
    }


}