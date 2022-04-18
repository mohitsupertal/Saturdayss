package com.shock.saturdaylifestyle.data.remote.repository

import com.shock.saturdaylifestyle.data.remote.entity.RegisterEntity
import com.shock.saturdaylifestyle.data.remote.source.DataService
import io.reactivex.Single
import javax.inject.Inject

class TravelRepositoryImpl @Inject constructor(private val dataService: DataService) : TravelRepository {

    override fun getStringResponse(): Single<String> = dataService.getHighlightsResponse()

    override fun getIncludedResponse(): Single<String>  = dataService.getIncludedResponse()

    override fun registerUser(name: String,
                              phoneNumber: String,
                              countryCode: String,
                              genderType: Int,
                              referral: String): Single<RegisterEntity> = dataService.registerUser(name, phoneNumber, countryCode, genderType, referral)

    override fun otpSend(phoneNumber: String, countryCode: String): Single<RegisterEntity> = dataService.OtpSend(phoneNumber, countryCode)

    override fun otpVerify(otp: String): Single<RegisterEntity> = dataService.OtpVerify(otp)
}