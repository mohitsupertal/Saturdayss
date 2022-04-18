package com.shock.saturdaylifestyle.view.login_register

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shock.saturdaylifestyle.data.remote.repository.TravelRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class LoginViewModel(private val travelService: TravelRepository) : ViewModel() {

    var responseLiveData : MutableLiveData<Boolean> = MutableLiveData()
    var errorLiveData : MutableLiveData<Throwable> = MutableLiveData()
    var progressLiveData : MutableLiveData<Boolean> = MutableLiveData()

    fun otpSend(phoneNumber: String, countryCode: String) {

        travelService.otpSend(phoneNumber, countryCode)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.d("*** RESPONSE >>>", ""+it.data)
                responseLiveData.postValue(it.success)
            }, {
                Log.d("*** ERROR >>>", ""+it.message)
                     errorLiveData.postValue(it)
            })
    }
}