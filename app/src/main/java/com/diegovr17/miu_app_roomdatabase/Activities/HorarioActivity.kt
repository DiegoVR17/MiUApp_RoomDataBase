package com.diegovr17.miu_app_roomdatabase.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.room.Room
import com.diegovr17.miu_app_roomdatabase.DataBase
import com.diegovr17.miu_app_roomdatabase.MainActivity
import com.diegovr17.miu_app_roomdatabase.R
import com.diegovr17.miu_app_roomdatabase.Tables.Horario
import kotlinx.android.synthetic.main.activity_horario.*
import java.sql.Types.NULL

class HorarioActivity : AppCompatActivity() {

    private lateinit var db: DataBase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_horario)

        var ban = 0

        db = Room.databaseBuilder(applicationContext,DataBase::class.java,"MyDatabase"
        ).allowMainThreadQueries().build()

        radioG.setOnCheckedChangeListener{ group, checkedId ->

            if(checkedId == R.id.Add){
                nombreCurso.isEnabled = true
                diaInicial.isEnabled = true
                diaFinal.isEnabled = true
                horaInicial.isEnabled = true
                horaFinal.isEnabled = true

                btnAddHorario.text = "AGREGAR CURSO"
                ban = 0
            }

            if(checkedId == R.id.Delete){
                nombreCurso.isEnabled = true
                diaInicial.isEnabled = false
                diaFinal.isEnabled = false
                horaInicial.isEnabled = false
                horaFinal.isEnabled = false

                btnAddHorario.text = "BORRAR CURSO"
                ban = 1
            }

            if(checkedId == R.id.Update){
                nombreCurso.isEnabled = true
                diaInicial.isEnabled = true
                diaFinal.isEnabled = true
                horaInicial.isEnabled = true
                horaFinal.isEnabled = true

                btnAddHorario.text = "ACTUALIZAR CURSO"
                ban = 2
            }
        }

        btnAddHorario.setOnClickListener{
            if(ban == 0){
                if(nameCurso.text.toString() == ""){
                    Toast.makeText(this,"Digite el nombre del curso", Toast.LENGTH_SHORT).show()
                }
                if(dInicial.text.toString() == ""){
                    Toast.makeText(this,"Digite el día inicial del curso", Toast.LENGTH_SHORT).show()
                }
                if(dFinal.text.toString() == ""){
                    Toast.makeText(this,"Digite el dia final del curso", Toast.LENGTH_SHORT).show()
                }
                if(hInicial.text.toString() == ""){
                    Toast.makeText(this,"Digite la hora inicial del curso", Toast.LENGTH_SHORT).show()
                }
                if(hFinal.text.toString() == ""){
                    Toast.makeText(this,"Digite la hora final del curso", Toast.LENGTH_SHORT).show()
                }
                else{
                    val horario = Horario(NULL,nameCurso.text.toString(),dInicial.text.toString(),
                        dFinal.text.toString(),hInicial.text.toString().toInt(),hFinal.text.toString().toInt())
                    Thread(Runnable{
                        db.horarioDao().insertCurso(horario)
                    }).start()
                    goToMainActivity()
                }
            }
            else if (ban == 1){
                val nombCurso = nameCurso.text.toString()
                if(nombCurso == ""){
                    Toast.makeText(this,"Digite el nombre del curso", Toast.LENGTH_SHORT).show()
                }
                else{
                Thread(Runnable{
                    val cursoDelete = db.horarioDao().findByCurso(nombCurso)
                    runOnUiThread {
                        if (cursoDelete != null) {
                            val curso = db.horarioDao().deleteCurso(Horario(cursoDelete.id, cursoDelete.nombCurso,
                                    cursoDelete.diaIni,cursoDelete.diaFin,cursoDelete.horaIn,cursoDelete.horaFin))
                            Toast.makeText(this,"Curso eliminado", Toast.LENGTH_SHORT).show()
                        }else {
                            Toast.makeText(this,"Curso no encontrado", Toast.LENGTH_SHORT).show()
                        }
                    }
                }).start()
                goToMainActivity()
                }
            }
            else{
                val nombCurso = nameCurso.text.toString()
                if(nombCurso == ""){
                    Toast.makeText(this,"Digite el nombre del curso", Toast.LENGTH_SHORT).show()
                }
                if(dInicial.text.toString() == ""){
                    Toast.makeText(this,"Digite el día inicial del curso", Toast.LENGTH_SHORT).show()
                }
                if(dFinal.text.toString() == ""){
                    Toast.makeText(this,"Digite el dia final del curso", Toast.LENGTH_SHORT).show()
                }
                if(hInicial.text.toString() == ""){
                    Toast.makeText(this,"Digite la hora inicial del curso", Toast.LENGTH_SHORT).show()
                }
                if(hFinal.text.toString() == ""){
                    Toast.makeText(this,"Digite la hora final del curso", Toast.LENGTH_SHORT).show()
                }
                else{
                    Thread(Runnable{
                        val cursoUpdate = db.horarioDao().findByCurso(nombCurso)
                        runOnUiThread{
                            if(cursoUpdate != null){
                                val curso = db.horarioDao().updateCurso(Horario(cursoUpdate.id,cursoUpdate.nombCurso,
                                    dInicial.text.toString(),dFinal.text.toString(),hInicial.text.toString().toInt(),
                                    hFinal.text.toString().toInt()))
                                    Toast.makeText(this,"Curso actualizada",Toast.LENGTH_SHORT).show()
                            }else {
                                Toast.makeText(this,"Curso no encontrado",Toast.LENGTH_SHORT).show()
                            }
                        }
                    }).start()
                    goToMainActivity()
                }
            }
        }

    }

    private fun goToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}
