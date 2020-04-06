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
import com.diegovr17.miu_app_roomdatabase.Activities.ApunteActivity
import com.diegovr17.miu_app_roomdatabase.Adapters.ApunteAdapter
import com.diegovr17.miu_app_roomdatabase.ContextNotas
import com.diegovr17.miu_app_roomdatabase.DataBase
import com.diegovr17.miu_app_roomdatabase.R
import com.diegovr17.miu_app_roomdatabase.Tables.Apunte
import com.diegovr17.miu_app_roomdatabase.Tables.Curso
import kotlinx.android.synthetic.main.calendario_fragment.view.*

class CalendarioFragment : Fragment(){

    lateinit var db: DataBase
    lateinit var mView: View
    var allApuntes: List<Apunte> = emptyList()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        db = Room.databaseBuilder(ContextNotas.context,DataBase::class.java,"MyDatabase"
        ).allowMainThreadQueries().build()

        mView = inflater.inflate(R.layout.calendario_fragment,container,false)

        val rv_apuntes = mView.findViewById<RecyclerView>(R.id.rv_apuntes)
        rv_apuntes.layoutManager = LinearLayoutManager(
            activity!!.applicationContext,
            RecyclerView.VERTICAL,
            false
        )
        rv_apuntes.setHasFixedSize(true)

        allApuntes = db.apunteDao().getAllApuntes()

        var apuntesAdapter = ApunteAdapter(
            activity!!.applicationContext,
            allApuntes as ArrayList<Apunte>
        )

        rv_apuntes.adapter = apuntesAdapter

        apuntesAdapter.notifyDataSetChanged()
        openAddCalendario()
        return mView
    }

    private fun openAddCalendario() {
        mView.btnAddApun.setOnClickListener{
            val intent = Intent(ContextNotas.context, ApunteActivity::class.java)
            startActivity(intent)
        }
    }
}