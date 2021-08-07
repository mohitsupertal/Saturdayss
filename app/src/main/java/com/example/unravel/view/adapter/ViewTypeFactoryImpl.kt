package com.example.unravel.view.adapter

import android.view.View
import android.view.ViewGroup
import com.example.unravel.R
import java.lang.NullPointerException

class ViewTypeFactoryImpl : ViewTypeFactory {

    companion object {
        private const val TravelCheck = R.layout.sub_item
        private const val RelatedItems = R.layout.related_items
    }
    override fun type(travelSubData: TravelSubData): Int {
        return when(travelSubData.viewType){
            "travelCheck" -> TravelCheck

            "relatedItems" -> RelatedItems

            else -> 0
        }

        }

    override fun create(view: View, parent: ViewGroup, viewType: Int): BaseViewHolder<TravelSubData> {
        when(viewType) {

           TravelCheck -> return TravelCheckVH(view)

           RelatedItems -> {
               view.layoutParams = ViewGroup.LayoutParams((parent.measuredWidth * 0.7).toInt(),ViewGroup.LayoutParams.WRAP_CONTENT)
               return RelateItemVH(view)
           }

           else -> throw NullPointerException()
       }
    }

}