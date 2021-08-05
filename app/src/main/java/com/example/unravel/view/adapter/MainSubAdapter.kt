package com.example.unravel.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.unravel.R

class MainSubAdapter private constructor(private val diffUtil: DiffUtil.ItemCallback<TravelSubData>)
    : ListAdapter<TravelSubData, MainSubAdapter.MainSubViewHolder>(diffUtil) {

    companion object{
        private val diffUtil = object : DiffUtil.ItemCallback<TravelSubData>(){
            override fun areItemsTheSame(oldItem: TravelSubData, newItem: TravelSubData): Boolean {
                return oldItem.id == newItem.id
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainSubViewHolder {
        return MainSubViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.sub_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MainSubViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class MainSubViewHolder(view : View) : RecyclerView.ViewHolder(view){

        private val tvOption : TextView = view.findViewById(R.id.tv_option)

        fun bind(data: TravelSubData){

            tvOption.text = data.name
        }
    }
}

data class TravelSubData(val id : Int, val name : String)