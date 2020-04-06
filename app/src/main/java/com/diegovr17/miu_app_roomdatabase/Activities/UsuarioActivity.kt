package com.diegovr17.miu_app_roomdatabase.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.room.Room
import com.diegovr17.miu_app_roomdatabase.DataBase
import com.diegovr17.miu_app_roomdatabase.MainActivity
import com.diegovr17.miu_app_roomdatabase.R
import com.diegovr17.miu_app_roomdatabase.Tables.Usuario
import kotlinx.android.synthetic.main.activity_usuario.*
import java.sql.Types.NULL

class UsuarioActivity : AppCompatActivity() {

    private lateinit var db: DataBase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_usuario)

        var ban = 0

        db = Room.databaseBuilder(applicationContext,DataBase::class.java,"MyDatabase"
        ).allowMainThreadQueries().build()

        radioG.setOnCheckedChangeListener{ group, checkedId ->

                if(checkedId == R.id.Add){
                    nomUsuario.isEnabled = true
                    apelliUsuario.isEnabled = true
                    emailUsuario.isEnabled = true
                    passUsuario.isEnabled = true
                    carreUsuario.isEnabled = true

                    btnAddUsuario.text = "AGREGAR USUARIO"
                    ban = 0
                }

                if(checkedId == R.id.Delete){
                    nomUsuario.isEnabled = true
                    apelliUsuario.isEnabled = false
                    emailUsuario.isEnabled = false
                    passUsuario.isEnabled = false
                    carreUsuario.isEnabled = false

                    btnAddUsuario.text = "BORRAR USUARIO"
                    ban = 1
                }

                if(checkedId == R.id.Update){
                    nomUsuario.isEnabled = true
                    apelliUsuario.isEnabled = false
                    emailUsuario.isEnabled = false
                    passUsuario.isEnabled = true
                    carreUsuario.isEnabled = true

                    btnAddUsuario.text = "ACTUALIZAR USUARIO"
                    ban = 2
                }
            }

        btnAddUsuario.setOnClickListener{
            if(ban == 0){
                if(nameUser.text.toString() == ""){
                    Toast.makeText(this,"Digite el nombre de usuario", Toast.LENGTH_SHORT).show()
                }
                else if(LnameUser.text.toString() == ""){
                    Toast.makeText(this,"Digite el apellido de usario", Toast.LENGTH_SHORT).show()
                }
                else if(User.text.toString() == ""){
                    Toast.makeText(this,"Digite el correo de usuario", Toast.LENGTH_SHORT).show()
                }
                else if(keyUser.text.toString() == ""){
                    Toast.makeText(this,"Digite la contraseña de usuario", Toast.LENGTH_SHORT).show()
                }
                else if(CUser.text.toString() == ""){
                    Toast.makeText(this,"Digite la carrera universitaria", Toast.LENGTH_SHORT).show()
                }
                else{
                    val password = keyUser.text.toString()
                    if(password.length >= 6){
                        val usuario = Usuario(NULL,nameUser.text.toString(),LnameUser.text.toString(),User.text.toString(),
                            keyUser.text.toString(),CUser.text.toString())
                        Thread(Runnable{
                            db.usuarioDao().insertUser(usuario)
                        }).start()
                        goToMainActivity()
                    }
                    else{
                    Toast.makeText(this,"La contraseña debe tener como mínímo 6 digitos", Toast.LENGTH_SHORT).show()
                    }
                }
            }
            else if (ban == 1){
                val nomUsuario = nameUser.text.toString()
                if(nomUsuario == ""){
                    Toast.makeText(this,"Digite el nombre de usuario", Toast.LENGTH_SHORT).show()
                }
                else{
                Thread(Runnable{
                    val usuarioDelete = db.usuarioDao().findByName(nomUsuario)
                    runOnUiThread {
                        if (usuarioDelete != null) {
                            val usuario = db.usuarioDao().deleteUser(Usuario(usuarioDelete.id, usuarioDelete.nombre,
                                usuarioDelete.apellido,usuarioDelete.correo,usuarioDelete.password,usuarioDelete.carrera)
                            )
                            Toast.makeText(this,"Usuario eliminado", Toast.LENGTH_SHORT).show()
                        }else {
                            Toast.makeText(this,"Usuario no encontrado", Toast.LENGTH_SHORT).show()
                        }
                    }
                }).start()
                goToMainActivity()
               }
            }
            else{
                val nomUsuario = nameUser.text.toString()
                if(nomUsuario == ""){
                    Toast.makeText(this,"Digite el nombre de usuario", Toast.LENGTH_SHORT).show()
                }
                else if(keyUser.text.toString() == ""){
                    Toast.makeText(this,"Digite la contraseña de usuario", Toast.LENGTH_SHORT).show()
                }
                else if(CUser.text.toString() == ""){
                    Toast.makeText(this,"Digite la carrera universitaria", Toast.LENGTH_SHORT).show()
                }
                else{
                Thread(Runnable{
                    val usuarioUpdate = db.usuarioDao().findByName(nomUsuario)
                    runOnUiThread{
                        if(usuarioUpdate != null){
                            val usuario = db.usuarioDao().updateUser(Usuario(usuarioUpdate.id,usuarioUpdate.nombre,
                                usuarioUpdate.apellido,usuarioUpdate.correo,keyUser.text.toString(),CUser.text.toString())
                            )

                            Toast.makeText(this,"Usuario actualizado",Toast.LENGTH_SHORT).show()
                        }else {
                            Toast.makeText(this,"Usuario no encontrado",Toast.LENGTH_SHORT).show()
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
