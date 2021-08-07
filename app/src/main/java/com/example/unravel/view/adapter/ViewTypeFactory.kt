package com.example.unravel.view.adapter

import android.view.View
import android.view.ViewGroup
import android.view.ViewParent

interface ViewTypeFactory {

    fun type(travelSubData: TravelSubData) : Int
    fun create(view: View, parent: ViewGroup, viewType : Int) : BaseViewHolder<TravelSubData>
}