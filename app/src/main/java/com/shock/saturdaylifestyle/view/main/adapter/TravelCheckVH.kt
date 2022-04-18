package com.shock.saturdaylifestyle.view.main.adapter

import android.view.View
import android.widget.TextView
import saturdaylifestyle.R

class TravelCheckVH(private val view: View) : BaseViewHolder<TravelSubData>(view) {

   private val tvOption : TextView = view.findViewById(R.id.tv_option)

    override fun bind(data: TravelSubData) {

        tvOption.text = data.name
    }
}