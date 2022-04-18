package com.shock.saturdaylifestyle.view.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shock.saturdaylifestyle.data.remote.entity.ZippedData
import com.shock.saturdaylifestyle.data.remote.repository.TravelRepository
import com.shock.saturdaylifestyle.view.TravelData
import com.shock.saturdaylifestyle.view.main.adapter.TravelSubData
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers
import org.jsoup.Jsoup
import saturdaylifestyle.R

class MainViewModel(private val travelService: TravelRepository) : ViewModel() {

    companion object {
        private const val menuItem = "menuItem"
        private const val relatedItem = "relatedItems"
    }
    var travelResLiveData : MutableLiveData<List<TravelData>> = MutableLiveData()
    var errorLiveData : MutableLiveData<Throwable> = MutableLiveData()
    var progressLiveData : MutableLiveData<Boolean> = MutableLiveData()

    private var travelData : MutableList<TravelData> = mutableListOf()
    private var subtravelData : MutableList<TravelSubData> = mutableListOf()

    fun getResponse(){
        progressLiveData.value = true
        Single.zip(travelService.getStringResponse().subscribeOn(Schedulers.io()),
            travelService.getIncludedResponse().subscribeOn(Schedulers.io()),

            object : BiFunction<String, String, ZippedData> {
                override fun apply(highligt: String, included: String): ZippedData {
                    return ZippedData(highligt, included)
                }
            })
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe( { zippedData ->
                progressLiveData.value = false
                travelResLiveData.postValue(prepareList(zippedData))
            }, { error ->
                progressLiveData.value = false
                errorLiveData.postValue(error)
            })
    }

    private fun prepareList(success: ZippedData) : List<TravelData> {
        subtravelData.clear()
        travelData.clear()
        //include data converter
        val doc: org.jsoup.nodes.Document? = Jsoup.parse(success.includedData)
        val includedString = doc?.text() ?: ""
        val includeSubArr = includedString
            .split(".")
            .map { name ->
            TravelSubData(name = name, viewType = menuItem)
        }

        //highlight data converter
        val highlightDoc: org.jsoup.nodes.Document? = Jsoup.parse(success.highlightData)
        val highlightString = highlightDoc?.text() ?: ""
        val highlightSubArr = highlightString
            .split(".")
            .map { name ->
            TravelSubData(name = name, viewType = menuItem)
        }

        subtravelData.add(TravelSubData(placeName = "Pragser Wildsee, Italy", viewType = relatedItem, img = R.drawable.pic_two))
        subtravelData.add(TravelSubData(placeName = "Pragser Wildsee, Italy", viewType = relatedItem, img = R.drawable.photo_one))
        subtravelData.add(TravelSubData(placeName = "Pragser Wildsee, Italy", viewType = relatedItem, img = R.drawable.pic_three))
        subtravelData.add(TravelSubData(placeName = "Pragser Wildsee, Italy", viewType = relatedItem, img = R.drawable.pic_two))
        subtravelData.add(TravelSubData(placeName = "Pragser Wildsee, Italy", viewType = relatedItem, img = R.drawable.pic_three))


        travelData.add(TravelData(1, "Whats Included", includeSubArr as MutableList<TravelSubData>, false))
        travelData.add(TravelData(2,  "Check on day to day needs", highlightSubArr as MutableList<TravelSubData>, false))
        travelData.add(TravelData(3,  "Booking & Cancellation", highlightSubArr, false))
        travelData.add(TravelData(4,  "Related Trips", subtravelData, true))

        return travelData
    }
}