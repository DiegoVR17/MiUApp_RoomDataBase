package com.diegovr17.miu_app_roomdatabase.Fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.diegovr17.miu_app_roomdatabase.Activities.HorarioActivity
import com.diegovr17.miu_app_roomdatabase.Adapters.HorarioAdapter
import com.diegovr17.miu_app_roomdatabase.ContextNotas
import com.diegovr17.miu_app_roomdatabase.DataBase
import com.diegovr17.miu_app_roomdatabase.R
import com.diegovr17.miu_app_roomdatabase.Tables.Horario
import kotlinx.android.synthetic.main.horario_fragment.*
import kotlinx.android.synthetic.main.horario_fragment.view.*

class HorarioFragment : Fragment(){

    lateinit var db: DataBase
    lateinit var mView: View
    var allHorarios: List<Horario> = emptyList()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        db = Room.databaseBuilder(ContextNotas.context,DataBase::class.java,"MyDatabase"
        ).allowMainThreadQueries().build()

        mView = inflater.inflate(R.layout.horario_fragment,container,false)

        val rv_horario = mView.findViewById<RecyclerView>(R.id.rv_horario)
        rv_horario.layoutManager = LinearLayoutManager(
            activity!!.applicationContext,
            RecyclerView.VERTICAL,
            false
        )
        rv_horario.setHasFixedSize(true)

        allHorarios = db.horarioDao().getAllHoraio()

        var horarioAdapter = HorarioAdapter(
            activity!!.applicationContext,
            allHorarios as ArrayList<Horario>
        )

        rv_horario.adapter = horarioAdapter

        horarioAdapter.notifyDataSetChanged()

        openAddHorario()
        return mView
    }

    private fun openAddHorario() {
        mView.btnAddHour.setOnClickListener{
            val intent = Intent(ContextNotas.context, HorarioActivity::class.java)
            startActivity(intent)
        }
    }
}
