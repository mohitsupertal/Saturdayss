package com.example.unravel.view

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.unravel.data.remote.entity.ZippedData
import com.example.unravel.data.remote.repository.TravelRepository
import com.example.unravel.data.remote.source.DataService
import com.example.unravel.view.adapter.TravelSubData
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers
import org.jsoup.Jsoup
import org.w3c.dom.Document

class MainViewModel(private val travelService: TravelRepository) : ViewModel() {

    var strResLiveData : MutableLiveData<List<TravelData>> = MutableLiveData()
    var errorLiveData : MutableLiveData<Throwable> = MutableLiveData()

    private var travelData : MutableList<TravelData> = mutableListOf()
    private var travelSubData : MutableList<TravelSubData> = mutableListOf()

    fun getResponse(){
        Single.zip(travelService.getStringResponse().subscribeOn(Schedulers.io()),
            travelService.getIncludedResponse().subscribeOn(Schedulers.io()),

            object : BiFunction<String, String, ZippedData> {
                override fun apply(highligt: String, included: String): ZippedData {
                    return ZippedData(highligt, included)
                }
            })
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe( { zippedData ->
                strResLiveData.postValue(prepareList(zippedData))
            }, { error ->
                errorLiveData.postValue(error)
            })
    }
//    fun getStringResponse() {
//        travelService.getStringResponse()
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe { success, error ->
//                //prepare the data
//                strResLiveData.postValue(prepareList(success))
//                //throw error
//                errorLiveData.postValue(error)
//            }
//    }

    private fun prepareList(success: ZippedData) : List<TravelData> {
        Log.d("*** PrepareList >>>", ""+success)
        val doc: org.jsoup.nodes.Document? = Jsoup.parse(success.includedData)
        val includedString = doc?.text() ?: ""
        val includeSubArr = includedString
            .split(".")
            .map { name ->
            TravelSubData(name = name, viewType = "travelCheck")
        }

        val highlightDoc: org.jsoup.nodes.Document? = Jsoup.parse(success.highlightData)
        val highlightString = highlightDoc?.text() ?: ""
        val highlightSubArr = highlightString
            .split(".")
            .map { name ->
            TravelSubData(name = name, viewType = "travelCheck")
        }

        val travelList = mutableListOf<TravelSubData>()
        travelList.add(TravelSubData(placeName = "Six", viewType = "relatedItems"))
        travelList.add(TravelSubData(placeName = "Six", viewType = "relatedItems"))
        travelList.add(TravelSubData(placeName = "Six", viewType = "relatedItems"))
        travelList.add(TravelSubData(placeName = "Six", viewType = "relatedItems"))
        travelList.add(TravelSubData(placeName = "Six", viewType = "relatedItems"))


        travelData.add(TravelData(1, "Whats Included", includeSubArr as MutableList<TravelSubData>, false))
        travelData.add(TravelData(2,  "Check on day to day needs", highlightSubArr as MutableList<TravelSubData>, false))
        travelData.add(TravelData(3,  "Booking & Cancellation", highlightSubArr as MutableList<TravelSubData>, false))
        travelData.add(TravelData(4,  "Related Trips", travelList as MutableList<TravelSubData>, true))

        return travelData
    }
}