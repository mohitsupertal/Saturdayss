package com.example.unravel.data.remote.source

import io.reactivex.Single
import retrofit2.http.GET

interface DataService {

    @GET("acti_highlights/00002426-9dbc-46e7-96f3-ba611e764ccd_acti_highlights.html")
    fun getHighlightsResponse() : Single<String>

    @GET("acti_included/00002426-9dbc-46e7-96f3-ba611e764ccd_acti_included.html")
    fun getIncludedResponse() : Single<String>
}