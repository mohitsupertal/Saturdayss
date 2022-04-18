package com.shock.saturdaylifestyle.network

import com.shock.saturdaylifestyle.data.remote.source.DataService
import retrofit2.Retrofit

class ApiService(retrofit: Retrofit) {

    val dataService : DataService by lazy {
        retrofit.create(DataService::class.java)
    }
}