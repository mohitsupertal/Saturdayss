package com.example.unravel.view.adapter

import android.view.View
import android.widget.TextView
import com.example.unravel.R

class RelateItemVH(private val view : View)
    : BaseViewHolder<TravelSubData>(view) {

   private val placeName : TextView = view.findViewById(R.id.tv_place_name)

    override fun bind(data: TravelSubData) {
        placeName.text = data.placeName
    }
}