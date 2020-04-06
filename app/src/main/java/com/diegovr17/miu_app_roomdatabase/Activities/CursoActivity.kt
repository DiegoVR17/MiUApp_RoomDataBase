package com.diegovr17.miu_app_roomdatabase.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.room.Room
import com.diegovr17.miu_app_roomdatabase.DataBase
import com.diegovr17.miu_app_roomdatabase.MainActivity
import com.diegovr17.miu_app_roomdatabase.R
import com.diegovr17.miu_app_roomdatabase.Tables.Curso
import kotlinx.android.synthetic.main.activity_curso.*
import java.sql.Types.NULL

class CursoActivity : AppCompatActivity() {

    private lateinit var db: DataBase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_curso)


        db = Room.databaseBuilder(applicationContext,DataBase::class.java,"MyDatabase"
        ).allowMainThreadQueries().build()

        var ban = 0

        radioG.setOnCheckedChangeListener{ group, checkedId ->

            if(checkedId == R.id.Add){
                nCurso.isEnabled = true
                nProfesor.isEnabled = true
                nCreditos.isEnabled = true

                btnAddCurso.text = "AGREGAR CURSO"
                ban = 0
            }

            if(checkedId == R.id.Delete){
                nCurso.isEnabled = true
                nProfesor.isEnabled = false
                nCreditos.isEnabled = false

                btnAddCurso.text = "BORRAR CURSO"
                ban = 1
            }

            if(checkedId == R.id.Update){
                nCurso.isEnabled = true
                nProfesor.isEnabled = true
                nCreditos.isEnabled = true

                btnAddCurso.text = "ACTUALIZAR CURSO"
                ban = 2
            }
        }

        btnAddCurso.setOnClickListener{
            if(ban == 0){
                if(nCurso.text.toString() == ""){
                    Toast.makeText(this,"Digite el nombre del curso", Toast.LENGTH_SHORT).show()
                }
                else if(nProfesor.text.toString() == ""){
                    Toast.makeText(this,"Digite el nombre del profesor", Toast.LENGTH_SHORT).show()
                }
                else if(nCreditos.text.toString() == ""){
                    Toast.makeText(this,"Digite el número de creditos del curso", Toast.LENGTH_SHORT).show()
                }
                else{
                    val curso = Curso(NULL,nCurso.text.toString(),nProfesor.text.toString(),nCreditos.text.toString().toInt())
                    Thread(Runnable{
                        db.cursoDao().insertCurso(curso)
                    }).start()
                    goToMainActivity()
                }
            }
            else if (ban == 1){
                val nombCurso = nCurso.text.toString()
                if(nombCurso == ""){
                    Toast.makeText(this,"Digite el nombre del Curso", Toast.LENGTH_SHORT).show()
                }
                else{
                    Thread(Runnable{
                        val cursoDelete = db.cursoDao().findByCurso(nombCurso)
                        runOnUiThread {
                            if (cursoDelete != null) {
                                val curso = db.cursoDao().deleteCurso(Curso(cursoDelete.id, cursoDelete.nombre,
                                cursoDelete.profesor,cursoDelete.creditos))
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
                val nombCurso = nCurso.text.toString()
                if(nombCurso == ""){
                    Toast.makeText(this,"Digite el nombre del curso", Toast.LENGTH_SHORT).show()
                }
                else if(nProfesor.text.toString() == ""){
                    Toast.makeText(this,"Digite el nombre del profesor", Toast.LENGTH_SHORT).show()
                }
                else if(nCreditos.text.toString() == ""){
                    Toast.makeText(this,"Digite el número de creditos del curso", Toast.LENGTH_SHORT).show()
                }
                else{
                    Thread(Runnable{
                        val cursoUpdate = db.cursoDao().findByCurso(nombCurso)
                        runOnUiThread{
                            if(cursoUpdate != null){
                                val curso = db.cursoDao().updateCurso(Curso(cursoUpdate.id,cursoUpdate.nombre,
                                    nProfesor.text.toString(),nCreditos.text.toString().toInt()))
                                    Toast.makeText(this,"Curso actualizado", Toast.LENGTH_SHORT).show()
                            }else {
                                Toast.makeText(this,"Curso no encontrado", Toast.LENGTH_SHORT).show()
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
