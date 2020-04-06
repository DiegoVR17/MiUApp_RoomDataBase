package com.diegovr17.miu_app_roomdatabase.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.diegovr17.miu_app_roomdatabase.R
import com.diegovr17.miu_app_roomdatabase.Tables.Apunte
import kotlinx.android.synthetic.main.calendario_item.view.*

class ApunteAdapter (

    var context: Context,
    var apunteList: ArrayList<Apunte>
) : RecyclerView.Adapter<ApunteAdapter.ApunteViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ApunteViewHolder {
        var itemView = LayoutInflater.from(context).inflate(R.layout.calendario_item,parent,false)
        return ApunteViewHolder(itemView,context)
    }

    override fun getItemCount(): Int = apunteList.size

    override fun onBindViewHolder(holder: ApunteViewHolder, position: Int) {
        val apunte: Apunte = apunteList[position]
        holder.bindApunte(apunte)
    }


    class ApunteViewHolder(
        itemView: View,
        context: Context
    ) : RecyclerView.ViewHolder(itemView){

        fun bindApunte(apunte: Apunte){
            itemView.tituApu.text = apunte.indice.toString() +". " + apunte.titulo
            itemView.desApu.text = apunte.descripcion
        }
    }
}