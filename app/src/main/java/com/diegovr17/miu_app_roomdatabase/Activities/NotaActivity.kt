package com.diegovr17.miu_app_roomdatabase.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.diegovr17.miu_app_roomdatabase.Adapters.NotaAdapter
import com.diegovr17.miu_app_roomdatabase.DataBase
import com.diegovr17.miu_app_roomdatabase.MainActivity
import com.diegovr17.miu_app_roomdatabase.R
import com.diegovr17.miu_app_roomdatabase.Tables.Nota
import kotlinx.android.synthetic.main.activity_nota.*

class NotaActivity : AppCompatActivity() {

    lateinit var db:DataBase
    var notas: List<Nota> = emptyList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nota)

        db = Room.databaseBuilder(applicationContext, DataBase::class.java,"MyDatabase"
        ).allowMainThreadQueries().build()

        var data = intent
        val nameC = data.getStringExtra("nombreCurso")
        tvHelph.text = nameC

        val rv_notas = findViewById<RecyclerView>(R.id.rv_notas)
        rv_notas.layoutManager = LinearLayoutManager(
            this,
            RecyclerView.VERTICAL,
            false
        )

        rv_notas.setHasFixedSize(true)

        notas = db.notaDao().getAllNotas()


        var notasAdapter = NotaAdapter(
                this,
        notas as ArrayList<Nota>
        )

        rv_notas.adapter = notasAdapter

        notasAdapter.notifyDataSetChanged()

        btnAddNota.setOnClickListener{

            goToNotaAddActivity()
        }

    }

    private fun goToNotaAddActivity() {
        var intent = Intent(this,NotaAddActivity::class.java)
        intent.putExtra("nomMate",tvHelph.text.toString())
        startActivity(intent)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}


