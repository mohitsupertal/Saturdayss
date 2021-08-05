package com.example.unravel.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.example.unravel.R
import com.example.unravel.view.adapter.TravelSubData

class MainActivity : AppCompatActivity() {

    private lateinit var rv_travel_list : RecyclerView
    private lateinit var mainAdapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val travelList = ArrayList<TravelSubData>()
        travelList.add(TravelSubData(1, "One"))
        travelList.add(TravelSubData(2, "Two"))
        travelList.add(TravelSubData(3, "Three"))
        travelList.add(TravelSubData(4, "Six"))
        travelList.add(TravelSubData(5, "Five"))
        travelList.add(TravelSubData(6, "Six"))

        val mainList : ArrayList<TravelData> = ArrayList<TravelData>()
        mainList.add(TravelData(1, "Whats Included", travelList, false))

        rv_travel_list = findViewById(R.id.rv_list)
        mainAdapter = MainAdapter.newInstance()
        rv_travel_list.adapter = mainAdapter
        mainAdapter.submitList(mainList)
    }
}