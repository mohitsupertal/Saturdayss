package com.saturdays.login_register.adapters

import android.app.Dialog
import android.content.Context
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.shock.saturdaylifestyle.view.login_register.LoginRegisterActivity
import com.shock.saturdaylifestyle.view.login_register.models.CountryDM
import saturdaylifestyle.R


class CountryAdapter(
    var context: Context,
    var mList: ArrayList<CountryDM>,
    var mDialogGet: Dialog
):
    RecyclerView.Adapter<CountryAdapter.MyViewHolder>(), View.OnClickListener
     {

    private var list: ArrayList<CountryDM> = ArrayList()
    private lateinit var dialogGet: Dialog


         init {
        this.list = mList
        this.dialogGet = mDialogGet
    }

         fun updateList( mList: ArrayList<CountryDM>)
         {
             this.list = mList
             notifyDataSetChanged()
         }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_country, parent, false)
        )
    }


    inner class MyViewHolder(view: View) :
        RecyclerView.ViewHolder(view) {

        val code = itemView.findViewById<TextView>(R.id.tv_code)
        val countryName = itemView.findViewById<TextView>(R.id.tv_countryName)
        val tick = itemView.findViewById<ImageView>(R.id.iv_tick)

        fun bind(data: CountryDM) {
            code.text = data.code
            countryName.text = data.countryName
        }
        }

    override fun getItemCount(): Int {

        return list.size

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        var data = list[position]

        holder.bind(data)

        if (data.isSelected!!) {
            holder.tick.visibility = View.VISIBLE
            data.code?.let { (context as LoginRegisterActivity).setSelectedCountry(it) }
        }
        else
            holder.tick.visibility=View.INVISIBLE

        holder.itemView.setOnClickListener {

            for (i in list)
            { i.isSelected = false }

            data.isSelected = true
            notifyDataSetChanged()

            Handler().postDelayed( { dialogGet.dismiss() }, 200)


        }



    }

    override fun onClick(p0: View?) {}


}