package com.diegovr17.miu_app_roomdatabase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.room.Room
import com.diegovr17.miu_app_roomdatabase.Activities.UsuarioActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private lateinit var db:DataBase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        var ban = 0

        db = Room.databaseBuilder(applicationContext,DataBase::class.java,"MyDatabase"
        ).allowMainThreadQueries().build()

        btnLogin.setOnClickListener{
            var usuario: String = userOK.text.toString()
            var password: String = passOK.text.toString()

            if (usuario == ""){
                Toast.makeText(this, "Introduzca un nombre de usuario", Toast.LENGTH_SHORT).show()
            }
            else if (password == ""){
                Toast.makeText(this, "Introduzca la contraseña para: " + usuario, Toast.LENGTH_SHORT).show()
            }
            else{
                Thread(Runnable{
                    val user = db.usuarioDao().findByName(usuario)
                    runOnUiThread {
                        if (user != null) {
                            if(user.nombre == usuario){
                                ban += 1
                            }

                            if(user.password == password){
                                ban += 1
                            }

                            if(ban == 2){
                                goToMainActivity()
                            }
                        }else {
                            Toast.makeText(this,"Usuario no registrado", Toast.LENGTH_SHORT).show()
                            goToUsuarioActivity()
                        }
                    }
                }).start()
            }
        }
    }

    private fun goToUsuarioActivity() {
        val intent = Intent(this,UsuarioActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun goToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}
