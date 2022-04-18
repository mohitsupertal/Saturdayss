package com.shock.saturdaylifestyle.view.otp

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shock.saturdaylifestyle.data.remote.repository.TravelRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class OTPViewModel(private val travelService: TravelRepository) : ViewModel() {

    var responseLiveData : MutableLiveData<Boolean> = MutableLiveData()
    var errorLiveData : MutableLiveData<Throwable> = MutableLiveData()
    var progressLiveData : MutableLiveData<Boolean> = MutableLiveData()


    fun otpVerify(otp: String) {

        travelService.otpVerify(otp)
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