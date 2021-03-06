package com.shock.saturdaylifestyle.view.main.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<TravelSubData>(view : View) : RecyclerView.ViewHolder(view){

    abstract fun bind(data : TravelSubData)
}