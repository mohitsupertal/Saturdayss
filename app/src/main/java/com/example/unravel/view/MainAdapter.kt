package com.example.unravel.view

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class MainAdapter private constructor(val diffUtil: DiffUtil.ItemCallback<TravelData>)
    : ListAdapter<TravelData, MainAdapter.MainViewHolder>(diffUtil) {

    companion object{

        val diffUtil = object : DiffUtil.ItemCallback<TravelData>() {
            override fun areItemsTheSame(oldItem: TravelData, newItem: TravelData): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: TravelData, newItem: TravelData): Boolean {
                return oldItem == newItem
            }

        }
        fun newInstance() : MainAdapter{
            return MainAdapter(diffUtil)
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItem(position: Int): TravelData {
        return super.getItem(position)
    }

    class MainViewHolder(view: View) : RecyclerView.ViewHolder(view){

        fun bind(travelData: TravelData){

            //set the view data
        }
    }

}

data class TravelData(val id : Int, val title : String)