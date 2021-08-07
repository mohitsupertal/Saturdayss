package com.example.unravel.view

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.unravel.R
import com.example.unravel.view.adapter.TravelSubData
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModel : MainViewModel

    private lateinit var rv_travel_list : RecyclerView
    private lateinit var mainAdapter: MainAdapter
    private lateinit var iv_toolimg : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.getResponse()

        setObserver()

        iv_toolimg = findViewById(R.id.iv_toolimg)
        rv_travel_list = findViewById(R.id.rv_list)

        Glide.with(this).load(R.drawable.pic_three).into(iv_toolimg)

        val onItemClick : (Int, Int, Boolean) -> Unit = {position, id, isExpand ->
            Log.d("*** onItemClick >>> ", ""+id)

            val expand = mainAdapter.getValueAtPosition(position).isExpanded
            mainAdapter.getValueAtPosition(position).isExpanded = !expand
            mainAdapter.notifyItemChanged(position)

        }

        mainAdapter = MainAdapter.newInstance(onItemClick)
        rv_travel_list.adapter = mainAdapter

    }

    private fun setObserver(){

        viewModel.strResLiveData.observe(this, Observer {
            mainAdapter.submitList(it as MutableList<TravelData>)
        })
    }
}