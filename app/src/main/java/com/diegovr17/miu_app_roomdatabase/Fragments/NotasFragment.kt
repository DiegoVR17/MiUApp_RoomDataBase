package com.diegovr17.miu_app_roomdatabase.Fragments

import android.content.Context
import android.content.Intent
import android.database.Observable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CursorAdapter
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.diegovr17.miu_app_roomdatabase.Activities.CursoActivity
import com.diegovr17.miu_app_roomdatabase.Adapters.CursoAdapter
import com.diegovr17.miu_app_roomdatabase.ContextNotas
import com.diegovr17.miu_app_roomdatabase.Daos.CursoDao
import com.diegovr17.miu_app_roomdatabase.DataBase
import com.diegovr17.miu_app_roomdatabase.R
import com.diegovr17.miu_app_roomdatabase.Tables.Curso
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.notas_fragment.*
import kotlinx.android.synthetic.main.notas_fragment.view.*
import java.util.*
import kotlin.collections.ArrayList

class NotasFragment : Fragment(){

    lateinit var db:DataBase
    lateinit var mView: View
    var allCursos: List<Curso> = emptyList()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        db = Room.databaseBuilder(ContextNotas.context,DataBase::class.java,"MyDatabase"
        ).allowMainThreadQueries().build()

        mView = inflater.inflate(R.layout.notas_fragment,container,false)

       val rv_cursos = mView.findViewById<RecyclerView>(R.id.rv_cursos)
        rv_cursos.layoutManager = LinearLayoutManager(
            activity!!.applicationContext,
            RecyclerView.VERTICAL,
            false
        )
        rv_cursos.setHasFixedSize(true)

        allCursos = db.cursoDao().getAllCursos()

        var cursosAdapter = CursoAdapter(
            activity!!.applicationContext,
            allCursos as ArrayList<Curso>
        )

        rv_cursos.adapter = cursosAdapter

        cursosAdapter.notifyDataSetChanged()
        openAddCurso()
        return mView
    }

    private fun openAddCurso() {
        mView.btnAddMateria.setOnClickListener{
            val intent = Intent(ContextNotas.context,CursoActivity::class.java)
            startActivity(intent)
        }
    }

}