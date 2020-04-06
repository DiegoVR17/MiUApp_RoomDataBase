package com.diegovr17.miu_app_roomdatabase.Daos

import androidx.room.*
import com.diegovr17.miu_app_roomdatabase.Tables.Usuario

@Dao
interface UsuarioDao{
    @Query("SELECT * FROM tabla_usuario")
    fun getAll():List<Usuario>

    @Query("SELECT * FROM tabla_usuario WHERE Nombre= :nombre")
    fun findByName(nombre:String): Usuario

    @Insert
    fun insertAllUser(vararg usuarios: Usuario)

    @Insert
    fun insertUser(usuario: Usuario)

    @Delete
    fun deleteUser(usuario: Usuario)

    @Update
    fun updateUser(usuario: Usuario)

}