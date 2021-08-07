package com.example.unravel.view.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.unravel.R

class MainSubAdapter private constructor(private val diffUtil: DiffUtil.ItemCallback<TravelSubData>)
    : ListAdapter<TravelSubData, BaseViewHolder<TravelSubData>>(diffUtil) {

    private val typeFactoryImpl = ViewTypeFactoryImpl()

    companion object{
        private val diffUtil = object : DiffUtil.ItemCallback<TravelSubData>(){
            override fun areItemsTheSame(oldItem: TravelSubData, newItem: TravelSubData): Boolean {
                return oldItem.name == newItem.name
            }

            override fun areContentsTheSame(
                oldItem: TravelSubData,
                newItem: TravelSubData
            ): Boolean {
                return oldItem == newItem
            }
        }

        fun newInstance() : MainSubAdapter{
            return MainSubAdapter(diffUtil)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<TravelSubData> {
        Log.d("*** MainSubAdapter ViewType onCreateVH >>>>", ""+viewType)
         when(viewType){

            1 -> {
                return TravelCheckVH(
                    LayoutInflater
                        .from(parent.context)
                        .inflate(R.layout.sub_item, parent, false)
                )
            }

            2 -> {
                val view = LayoutInflater
                        .from(parent.context)
                        .inflate(R.layout.related_items, parent, false)

                view.layoutParams = ViewGroup.LayoutParams((parent.measuredWidth * 0.9).toInt(),ViewGroup.LayoutParams.MATCH_PARENT)
                return RelateItemVH(view)
            }

            else ->{
                return throw NullPointerException()
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        Log.d("*** MainSubAdapter ViewType getItemVT >>>>", ""+getItem(position).viewType)

        return when(getItem(position).viewType){

            "travelCheck" -> 1

            "relatedItems" -> 2

            else -> 0
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder<TravelSubData>, position: Int) {
        Log.d("*** MainSubAdapter >>>", ""+getItem(position).name)
        holder.bind(getItem(position))
    }
}

data class TravelSubData(val name : String = "", val placeName : String = "", val viewType : String)