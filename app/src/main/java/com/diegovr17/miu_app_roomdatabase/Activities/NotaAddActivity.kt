package com.diegovr17.miu_app_roomdatabase.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.room.Room
import com.diegovr17.miu_app_roomdatabase.DataBase
import com.diegovr17.miu_app_roomdatabase.MainActivity
import com.diegovr17.miu_app_roomdatabase.R
import com.diegovr17.miu_app_roomdatabase.Tables.Nota
import kotlinx.android.synthetic.main.activity_nota_add.*
import kotlinx.android.synthetic.main.activity_nota_add.radioG
import java.sql.Types.NULL

class NotaAddActivity : AppCompatActivity() {

    private lateinit var db:DataBase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nota_add)

        var ban = 0


        var intent = intent
        val nomMate = intent.getStringExtra("nomMate")

        nomMat.text = nomMate

        db = Room.databaseBuilder(applicationContext,DataBase::class.java,"MyDatabase"
        ).allowMainThreadQueries().build()

        radioG.setOnCheckedChangeListener{ group, checkedId ->

            if(checkedId == R.id.Add){
                nomMat.isEnabled = false
                indiNota.isEnabled = true
                porceNota.isEnabled = true
                valNota.isEnabled = true

                btnAddNota.text = "AGREGAR NOTA"
                ban = 0
            }

            if(checkedId == R.id.Delete){
                nomMat.isEnabled = false
                indiNota.isEnabled = true
                porceNota.isEnabled = false
                valNota.isEnabled = false

                btnAddNota.text = "BORRAR NOTA"
                ban = 1
            }

            if(checkedId == R.id.Update){
                nomMat.isEnabled = false
                indiNota.isEnabled = true
                porceNota.isEnabled = true
                valNota.isEnabled = true

                btnAddNota.text = "ACTUALIZAR NOTA"
                ban = 2
            }
        }

        btnAddNota.setOnClickListener{
            if(ban == 0) {
                if (iNota.text.toString() == "") {
                    Toast.makeText(this, "Digite el indice de la nota", Toast.LENGTH_SHORT).show()
                } else if (pNota.text.toString() == "") {
                    Toast.makeText(this, "Digite el porcentaje de la nota", Toast.LENGTH_SHORT)
                        .show()
                } else if (vNota.text.toString() == "") {
                    Toast.makeText(this, "Digite el valor de la norta", Toast.LENGTH_SHORT).show()
                }
                else{
                    val nota = Nota(NULL,nomMat.text.toString(),iNota.text.toString().toInt(), pNota.text.toString().toFloat(),
                        vNota.text.toString().toFloat())
                    Thread(Runnable {
                        db.notaDao().insertNota(nota)
                    }).start()
                    goToMainActivity()
                }
            }
            else if (ban == 1) {
                val nomCurso = nomMat.text.toString()
                    Thread(Runnable {
                        val notaDelete = db.notaDao().findByNota(nomCurso)
                        runOnUiThread {
                            if (notaDelete != null) {
                                val nota = db.notaDao().deleteNota(
                                    Nota(
                                        notaDelete.id,notaDelete.nombreCur ,notaDelete.indice,
                                        notaDelete.porcentaje, notaDelete.valor
                                    )
                                )
                                Toast.makeText(this, "Nota eliminada", Toast.LENGTH_SHORT).show()
                            } else {
                                Toast.makeText(this, "Nota no encontrada", Toast.LENGTH_SHORT)
                                    .show()
                            }
                        }
                    }).start()
                goToMainActivity()

            }
            else{
                val nomCurso = nomMat.text.toString()
                if (iNota.text.toString() == "") {
                    Toast.makeText(this, "Digite el indice de la nota", Toast.LENGTH_SHORT).show()
                } else if (pNota.text.toString() == "") {
                    Toast.makeText(this, "Digite el porcentaje de la nota", Toast.LENGTH_SHORT)
                        .show()
                } else if (vNota.text.toString() == "") {
                    Toast.makeText(this, "Digite el valor de la norta", Toast.LENGTH_SHORT).show()
                }
                else{
                Thread(Runnable{
                    val notaUpdate = db.notaDao().findByNota(nomCurso)
                    runOnUiThread{
                        if(notaUpdate != null){
                            val nota = db.notaDao().updateNota(Nota(notaUpdate.id,notaUpdate.nombreCur
                                ,iNota.text.toString().toInt(),pNota.text.toString().toFloat(),vNota.text.toString().toFloat())
                            )

                            Toast.makeText(this,"Nota actualizada",Toast.LENGTH_SHORT).show()
                        }else {
                            Toast.makeText(this,"Nota no encontrada",Toast.LENGTH_SHORT).show()
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
        val intent = Intent(this, NotaActivity::class.java)
        startActivity(intent)
        finish()
    }



}
