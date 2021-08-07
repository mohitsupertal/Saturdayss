package com.example.unravel.data.remote.repository

import com.example.unravel.data.remote.source.DataService
import io.reactivex.Single
import javax.inject.Inject

class TravelRepositoryImpl @Inject constructor(private val dataService: DataService) : TravelRepository {

    override fun getStringResponse(): Single<String> = dataService.getHighlightsResponse()

    override fun getIncludedResponse(): Single<String>  = dataService.getIncludedResponse()
}