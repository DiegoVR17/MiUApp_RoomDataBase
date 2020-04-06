package com.diegovr17.miu_app_roomdatabase.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.room.Room
import com.diegovr17.miu_app_roomdatabase.DataBase
import com.diegovr17.miu_app_roomdatabase.MainActivity
import com.diegovr17.miu_app_roomdatabase.R
import com.diegovr17.miu_app_roomdatabase.Tables.Apunte
import kotlinx.android.synthetic.main.activity_apunte.*
import java.sql.Types.NULL

class ApunteActivity : AppCompatActivity() {

    private lateinit var db:DataBase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_apunte)

        db = Room.databaseBuilder(applicationContext,DataBase::class.java,"MyDatabase"
        ).allowMainThreadQueries().build()

        var ban = 0

        radioG.setOnCheckedChangeListener{ group, checkedId ->

            if(checkedId == R.id.Add){
                tituApunte.isEnabled = true
                descriApunte.isEnabled = true
                indiApunte.isEnabled = true

                btnAddApunte.text = "AGREGAR APUNTE"
                ban = 0
            }

            if(checkedId == R.id.Delete){
                tituApunte.isEnabled = false
                descriApunte.isEnabled = false
                indiApunte.isEnabled = true

                btnAddApunte.text = "BORRAR APUNTE"
                ban = 1
            }

            if(checkedId == R.id.Update){
                tituApunte.isEnabled = true
                descriApunte.isEnabled = true
                indiApunte.isEnabled = true

                btnAddApunte.text = "ACTUALIZAR APUNTE"
                ban = 2
            }
        }

        btnAddApunte.setOnClickListener{
            if(ban == 0){
                if(tApunte.text.toString() == ""){
                    Toast.makeText(this,"Digite el titulo del apunte", Toast.LENGTH_SHORT).show()
                }
                if(dApunte.text.toString() == ""){
                    Toast.makeText(this,"Digite la descripción del apunte", Toast.LENGTH_SHORT).show()
                }
                if(iApunte.text.toString() == ""){
                    Toast.makeText(this,"Digite el indice del apunte", Toast.LENGTH_SHORT).show()
                }
                else{
                    val apunte = Apunte(NULL,tApunte.text.toString(),dApunte.text.toString(),iApunte.text.toString().toInt())
                    Thread(Runnable{
                        db.apunteDao().insertApunte(apunte)
                    }).start()
                    goToMainActivity()
                }
            }
            else if (ban == 1){
                val indiApunte = iApunte.text.toString().toInt()
                if(iApunte.text.toString() == ""){
                    Toast.makeText(this,"Digite el indice del apunte", Toast.LENGTH_SHORT).show()
                }
                else{
                    Thread(Runnable{
                        val apunteDelete = db.apunteDao().findByIndiApunte(indiApunte)
                        runOnUiThread {
                            if (apunteDelete != null) {
                                val apunte = db.apunteDao().deleteApunte(Apunte(apunteDelete.id,apunteDelete.titulo,
                                    apunteDelete.descripcion,apunteDelete.indice))
                                    Toast.makeText(this,"Apunte eliminado", Toast.LENGTH_SHORT).show()
                            }else {
                                Toast.makeText(this,"Apunte no encontrado", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }).start()
                    goToMainActivity()
                }
            }
            else{
                val indiApunte = iApunte.text.toString().toInt()
                if(tApunte.text.toString() == ""){
                    Toast.makeText(this,"Digite el titulo del apunte", Toast.LENGTH_SHORT).show()
                }
                if(dApunte.text.toString() == ""){
                    Toast.makeText(this,"Digite la descripción del apunte", Toast.LENGTH_SHORT).show()
                }
                if(iApunte.text.toString() == ""){
                    Toast.makeText(this,"Digite el indice del apunte", Toast.LENGTH_SHORT).show()
                }
                else{
                    Thread(Runnable{
                        val apunteUpdate = db.apunteDao().findByIndiApunte(indiApunte)
                        runOnUiThread{
                            if(apunteUpdate != null){
                                val apunte = db.apunteDao().updateApunte(
                                    Apunte(apunteUpdate.id,tApunte.text.toString(),
                                        dApunte.text.toString(),apunteUpdate.indice))
                                    Toast.makeText(this,"Apunte actualizado", Toast.LENGTH_SHORT).show()
                            }else {
                                Toast.makeText(this,"Apunte no encontrado", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }).start()
                    goToMainActivity()
                }
            }
        }
    }

    private fun goToMainActivity() {
        val intent = Intent(this,MainActivity::class.java)
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

