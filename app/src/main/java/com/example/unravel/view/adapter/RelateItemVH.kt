package com.example.unravel.view.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.unravel.R

class RelateItemVH(private val view : View)
    : BaseViewHolder<TravelSubData>(view) {

   private val placeName : TextView = view.findViewById(R.id.tv_place_name)
   private val iv_img : ImageView = view.findViewById(R.id.iv_img)

    override fun bind(data: TravelSubData) {
        placeName.text = data.placeName
        Glide.with(view.context).load(data.img).into(iv_img)

    }
}