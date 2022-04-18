package com.shock.saturdaylifestyle.data.remote.repository

import com.shock.saturdaylifestyle.data.remote.entity.RegisterEntity
import io.reactivex.Single

interface TravelRepository {

    fun getStringResponse() : Single<String>

    fun getIncludedResponse() : Single<String>

    fun registerUser(name: String,
                     phoneNumber: String,
                     countryCode: String,
                     genderType: Int,
                     referral: String) : Single<RegisterEntity>

    fun otpSend(phoneNumber: String,
                countryCode: String) : Single<RegisterEntity>

    fun otpVerify(otp: String) : Single<RegisterEntity>

}