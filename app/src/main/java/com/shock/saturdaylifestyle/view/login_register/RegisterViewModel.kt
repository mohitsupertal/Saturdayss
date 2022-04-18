package com.shock.saturdaylifestyle.view.login_register

import android.util.Log
import androidx.lifecycle.ViewModel
import com.shock.saturdaylifestyle.data.remote.repository.TravelRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class RegisterViewModel(private val travelService: TravelRepository) : ViewModel() {

    fun register(name: String, phoneNumber: String, countryCode: String, genderType: Int, referral: String) {

        travelService.registerUser(name, phoneNumber, countryCode, genderType, referral)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.d("*** RESPONSE >>>", ""+it.data)
            //    pplLiveData.postValue(it.results)
            }, {
                Log.d("*** ERROR >>>", ""+it.message)
           //     errorLiveData.postValue(it)
            })
    }

}