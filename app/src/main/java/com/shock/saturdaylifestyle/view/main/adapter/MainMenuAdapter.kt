package com.shock.saturdaylifestyle.view.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import saturdaylifestyle.R

class MainSubAdapter private constructor(private val diffUtil: DiffUtil.ItemCallback<TravelSubData>)
    : ListAdapter<TravelSubData, BaseViewHolder<TravelSubData>>(diffUtil) {

    companion object{

        private const val menuItem = "menuItem"
        private const val relatedItem = "relatedItems"

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
         when(viewType){

            1 -> {
                return TravelCheckVH(
                    LayoutInflater
                        .from(parent.context)
                        .inflate(R.layout.menu_item, parent, false)
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

        return when(getItem(position).viewType){

            menuItem -> 1

            relatedItem -> 2

            else -> 0
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder<TravelSubData>, position: Int) {
        holder.bind(getItem(position))
    }
}

data class TravelSubData(val name : String = "", val placeName : String = "", val viewType : String, val img : Int = R.drawable.pic_three)