package com.example.unravel.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.unravel.R
import com.example.unravel.view.adapter.MainSubAdapter
import com.example.unravel.view.adapter.TravelSubData

class MainAdapter private constructor(val diffUtil: DiffUtil.ItemCallback<TravelData>)
    : ListAdapter<TravelData, MainAdapter.MainViewHolder>(diffUtil) {

    private var mainSubAdapter: MainSubAdapter = MainSubAdapter.newInstance()

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
        return MainViewHolder(LayoutInflater
            .from(parent.context)
            .inflate(R.layout.main_item, parent, false))
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(getItem(position), mainSubAdapter)
        holder.itemView.setOnClickListener {
            val expand = getItem(position).isExpanded
            getItem(position).isExpanded = !expand
            notifyItemChanged(position)
        }
    }

    class MainViewHolder(view: View) : RecyclerView.ViewHolder(view){

        private val tvTitle : TextView = view.findViewById(R.id.tv_title)
        private val rvSubList : RecyclerView = view.findViewById(R.id.rv_sublist)

        fun bind(travelData: TravelData, mainSubAdapter: MainSubAdapter){

            //set the view data
            tvTitle.text = travelData.title
            rvSubList.adapter = mainSubAdapter
            mainSubAdapter.submitList(travelData.subList)

            if (travelData.isExpanded){
                rvSubList.visibility = View.VISIBLE
            }else{
                rvSubList.visibility = View.GONE
            }

        }
    }
}

data class TravelData(val id : Int, val title : String, val subList : MutableList<TravelSubData>, var isExpanded : Boolean)