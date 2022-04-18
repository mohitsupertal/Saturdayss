package com.shock.saturdaylifestyle.data.remote.source

import com.shock.saturdaylifestyle.data.remote.entity.RegisterEntity
import io.reactivex.Single
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface DataService {

    @GET("acti_highlights/00002426-9dbc-46e7-96f3-ba611e764ccd_acti_highlights.html")
    fun getHighlightsResponse() : Single<String>

    @GET("acti_included/00002426-9dbc-46e7-96f3-ba611e764ccd_acti_included.html")
    fun getIncludedResponse() : Single<String>

    @FormUrlEncoded
    @POST("user/register")
    fun registerUser(
        @Field("name") name: String,
        @Field("mobile") phoneNumber: String,
        @Field("country_code") countryCode: String,
        @Field("gender") genderType: Int,
        @Field("referral_code") referral: String
    ): Single<RegisterEntity>

 @FormUrlEncoded
    @POST("user/otp/send")
    fun OtpSend(
        @Field("number") phoneNumber: String,
        @Field("country_code") countryCode: String
    ): Single<RegisterEntity>

    @FormUrlEncoded
    @POST("user/otp/verify")
    fun OtpVerify(
        @Field("otp") otp: String,
    ): Single<RegisterEntity>

}