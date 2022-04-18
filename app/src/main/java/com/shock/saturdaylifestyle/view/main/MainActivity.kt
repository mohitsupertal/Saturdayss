package com.shock.saturdaylifestyle.view.main

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.shock.saturdaylifestyle.view.MainAdapter
import com.shock.saturdaylifestyle.view.TravelData
import dagger.android.AndroidInjection
import saturdaylifestyle.R
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModel : MainViewModel

    private lateinit var rv_travel_list : RecyclerView
    private lateinit var mainAdapter: MainAdapter
    private lateinit var iv_toolimg : ImageView
    private lateinit var progress : ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.getResponse()

        setObserver()

        progress = findViewById(R.id.progress)
        iv_toolimg = findViewById(R.id.iv_toolimg)
        rv_travel_list = findViewById(R.id.rv_list)

        Glide.with(this).load(R.drawable.pic_three).into(iv_toolimg)

        val onItemClick : (Int, Int, Boolean) -> Unit = {position, id, isExpand ->

            val expand = mainAdapter.getValueAtPosition(position).isExpanded
            mainAdapter.getValueAtPosition(position).isExpanded = !expand
            mainAdapter.notifyItemChanged(position)

        }

        mainAdapter = MainAdapter.newInstance(onItemClick)
        rv_travel_list.adapter = mainAdapter

    }

    private fun setObserver(){

        viewModel.travelResLiveData.observe(this, Observer {
            mainAdapter.submitList(it as MutableList<TravelData>)
        })

        viewModel.progressLiveData.observe(this, Observer {
            if (it) {
                progress.visibility = View.VISIBLE
            }else{
                progress.visibility = View.GONE
            }
        })

      viewModel.errorLiveData.observe(this, Observer {
           Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show()

        })
    }
}