package com.diegovr17.miu_app_roomdatabase.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.diegovr17.miu_app_roomdatabase.R
import com.diegovr17.miu_app_roomdatabase.Tables.Nota
import kotlinx.android.synthetic.main.nota_item.view.*

class NotaAdapter (

    var context: Context,
    var notaList: ArrayList<Nota>
) : RecyclerView.Adapter<NotaAdapter.NotaViewHolder>(){

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NotaViewHolder {
        var itemView = LayoutInflater.from(context).inflate(R.layout.nota_item,parent,false)
        return NotaViewHolder(itemView,context)
    }

    override fun getItemCount(): Int = notaList.size

    override fun onBindViewHolder(holder: NotaViewHolder, position: Int) {
        val nota: Nota = notaList[position]
        holder.bindApunte(nota)
    }


    class NotaViewHolder(
        itemView: View,
        context: Context
    ) : RecyclerView.ViewHolder(itemView){

        fun bindApunte(nota: Nota){
            itemView.tituNota.text = nota.indice.toString() + ". " + nota.nombreCur
            itemView.datosNota.text = "Valor: " + nota.valor.toString() + " - Porcentaje: " + nota.porcentaje.toString() + " %"
        }
    }
}
