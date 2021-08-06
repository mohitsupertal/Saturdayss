package com.example.unravel.view

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.unravel.R
import com.example.unravel.network.ApiService
import com.example.unravel.view.adapter.TravelSubData
import okhttp3.OkHttpClient
import retrofit2.Retrofit


class MainActivity : AppCompatActivity() {

    private lateinit var rv_travel_list : RecyclerView
    private lateinit var mainAdapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val travelList = ArrayList<TravelSubData>()

        travelList.add(TravelSubData(6, placeName = "Six", viewType = "relatedItems"))
        travelList.add(TravelSubData(7, placeName = "Six", viewType = "relatedItems"))
        travelList.add(TravelSubData(8, placeName = "Six", viewType = "relatedItems"))
        travelList.add(TravelSubData(9, placeName = "Six", viewType = "relatedItems"))
        travelList.add(TravelSubData(11, placeName = "Six", viewType = "relatedItems"))

        var travel = ArrayList<TravelSubData>()
        travel.add(TravelSubData(1, "One", viewType = "travelCheck"))
        travel.add(TravelSubData(2, "Two", viewType = "travelCheck"))
        travel.add(TravelSubData(3, "Three", viewType = "travelCheck"))
        travel.add(TravelSubData(4, "Six", viewType = "travelCheck"))
        travel.add(TravelSubData(5, "Five", viewType = "travelCheck"))

        var travel2 = ArrayList<TravelSubData>()
        travel2.add(TravelSubData(1, "One", viewType = "travelCheck"))
        travel2.add(TravelSubData(2, "Two", viewType = "travelCheck"))
        travel2.add(TravelSubData(3, "Three", viewType = "travelCheck"))
        travel2.add(TravelSubData(4, "Six", viewType = "travelCheck"))
        travel2.add(TravelSubData(5, "Five", viewType = "travelCheck"))

        val mainList : ArrayList<TravelData> = ArrayList<TravelData>()
        mainList.add(TravelData(1, "Whats Included", travel, false))
        mainList.add(TravelData(2, "Check on day to day needs", travel , false))
        mainList.add(TravelData(3, "Related Trips", travelList, false))

        rv_travel_list = findViewById(R.id.rv_list)

        val onItemClick : (Int, Int, Boolean) -> Unit = {position, id, isExpand ->
            Log.d("*** onItemClick >>> ", ""+id)

            val expand = mainAdapter.getValueAtPosition(position).isExpanded
            mainAdapter.getValueAtPosition(position).isExpanded = !expand
            mainAdapter.notifyItemChanged(position)


        }
        mainAdapter = MainAdapter.newInstance(onItemClick)
        rv_travel_list.adapter = mainAdapter
        mainAdapter.submitList(mainList)

   //     webScraping()
    }

}