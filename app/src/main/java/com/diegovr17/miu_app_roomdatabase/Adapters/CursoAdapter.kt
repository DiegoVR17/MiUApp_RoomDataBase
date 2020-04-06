package com.diegovr17.miu_app_roomdatabase.Adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.diegovr17.miu_app_roomdatabase.Activities.NotaActivity
import com.diegovr17.miu_app_roomdatabase.ContextNotas
import com.diegovr17.miu_app_roomdatabase.R
import com.diegovr17.miu_app_roomdatabase.Tables.Curso
import kotlinx.android.synthetic.main.curso_item.view.*

class CursoAdapter (

    var context: Context,
    var cursoList: ArrayList<Curso>
) : RecyclerView.Adapter<CursoAdapter.CursoViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CursoViewHolder {
        var itemView = LayoutInflater.from(context).inflate(R.layout.curso_item,parent,false)
        return CursoViewHolder(itemView,context)
    }

    override fun getItemCount(): Int = cursoList.size

    override fun onBindViewHolder(holder: CursoViewHolder, position: Int){

        val curso: Curso = cursoList[position]
        holder.bindCurso(curso)

        holder.itemView.setOnClickListener{

            val model = cursoList.get(position)

            var nameCurso:String = model!!.nombre

            val intent = Intent(context,NotaActivity::class.java)
            intent.putExtra("nombreCurso",nameCurso)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)
        }

    }

    class CursoViewHolder(
        itemView: View,
        context: Context
    ) : RecyclerView.ViewHolder(itemView){

        fun bindCurso(curso: Curso){
            itemView.nomCur.text = curso.nombre
            itemView.nomPro.text = "Profesor: " + curso.profesor
            itemView.numCre.text = "Número de créditos: " + curso.creditos.toString()
        }
    }



}
