package com.example.unravel.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.unravel.R
import com.example.unravel.utils.SpacesItemDecoration
import com.example.unravel.view.adapter.MainSubAdapter
import com.example.unravel.view.adapter.TravelSubData


class MainAdapter private constructor(
    private val diffUtil: DiffUtil.ItemCallback<TravelData>,
    private val onItemClick: (Int, Int, Boolean) -> Unit
) : ListAdapter<TravelData, MainAdapter.MainViewHolder>(diffUtil) {

    private var mainSubAdapter: MainSubAdapter = MainSubAdapter.newInstance()
    private var context : Context? = null

    companion object{
        val diffUtil = object : DiffUtil.ItemCallback<TravelData>() {
            override fun areItemsTheSame(oldItem: TravelData, newItem: TravelData): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: TravelData, newItem: TravelData): Boolean {
                return oldItem == newItem
            }
        }

        fun newInstance(onItemClick: (Int, Int, Boolean) -> Unit) : MainAdapter{
            return MainAdapter(diffUtil, onItemClick)
        }
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        context = recyclerView.context
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        super.onDetachedFromRecyclerView(recyclerView)
        context = null
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.main_item, parent, false)
        )
    }

    fun getValueAtPosition(position: Int): TravelData{
        return getItem(position)
    }
    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(getItem(position), mainSubAdapter, onItemClick, context)
        holder.itemView.setOnClickListener {
            val expand = getItem(position).isExpanded
            getItem(position).isExpanded = !expand
            notifyItemChanged(position)
        }
    }

    class MainViewHolder(view: View) : RecyclerView.ViewHolder(view){

        private val tvTitle : TextView = view.findViewById(R.id.tv_title)
        private val rvSubList : RecyclerView = view.findViewById(R.id.rv_sublist)

        fun bind(
            travelData: TravelData,
            mainSubAdapter: MainSubAdapter,
            onItemClick: (Int, Int, Boolean) -> Unit,
            context: Context?
        ){

            //set the view data
            tvTitle.text = travelData.title

            if (travelData.id == 3) {
                val llmanager = LinearLayoutManager(
                    context,
                    LinearLayoutManager.HORIZONTAL,
                    true
                )
                rvSubList.layoutManager = llmanager

                rvSubList.addItemDecoration(SpacesItemDecoration(10))
//
//                 object : LinearLayoutManager(context) {
//                     override fun checkLayoutParams(lp: RecyclerView.LayoutParams): Boolean {
//                         // force height of viewHolder here, this will override layout_height from xml
//                         lp.height = height / 3
//                         return true
//                     }
//
//                 }
            }else{
                rvSubList.layoutManager = LinearLayoutManager(
                    context,
                    LinearLayoutManager.VERTICAL,
                    true
                )

            }

            //set sublist adapter
            rvSubList.adapter = mainSubAdapter
            mainSubAdapter.submitList(travelData.subList)

            if (travelData.isExpanded){
                rvSubList.visibility = View.VISIBLE
            }else{
                rvSubList.visibility = View.GONE
            }

            itemView.setOnClickListener {
         //       onItemClick(layoutPosition, travelData.id, travelData.isExpanded)
            }
        }
    }
}

data class TravelData(
    val id: Int,
    val title: String,
    val subList: MutableList<TravelSubData>,
    var isExpanded: Boolean
)