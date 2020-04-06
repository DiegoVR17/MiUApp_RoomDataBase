package com.diegovr17.miu_app_roomdatabase.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.diegovr17.miu_app_roomdatabase.R
import com.diegovr17.miu_app_roomdatabase.Tables.Horario
import kotlinx.android.synthetic.main.horario_item.view.*

class HorarioAdapter (

    var context: Context,
    var horarioList: ArrayList<Horario>
) : RecyclerView.Adapter<HorarioAdapter.HorarioViewHolder>(){

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HorarioViewHolder {
        var itemView = LayoutInflater.from(context).inflate(R.layout.horario_item,parent,false)
        return HorarioViewHolder(itemView,context)
    }

    override fun getItemCount(): Int = horarioList.size

    override fun onBindViewHolder(holder: HorarioViewHolder, position: Int){

        val horario: Horario = horarioList[position]
        holder.bindHorario(horario)
    }

    class HorarioViewHolder(
        itemView: View,
        context: Context
    ) : RecyclerView.ViewHolder(itemView){

        fun bindHorario(horario: Horario){
            itemView.nomCur.text = horario.nombCurso
            itemView.dias.text = horario.diaIni + " - " +horario.diaFin
            itemView.horas.text = horario.horaIn.toString() + " - " + horario.horaFin

        }
    }
}