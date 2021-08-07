package com.example.unravel.data.remote.repository

import io.reactivex.Single
import javax.inject.Singleton

interface TravelRepository {

    fun getStringResponse() : Single<String>

    fun getIncludedResponse() : Single<String>
}