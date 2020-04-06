package com.diegovr17.miu_app_roomdatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.diegovr17.miu_app_roomdatabase.Daos.*
import com.diegovr17.miu_app_roomdatabase.Tables.*

@Database(entities = arrayOf(Usuario::class, Nota::class,Curso::class, Horario::class, Apunte::class), version = 1)
abstract class DataBase: RoomDatabase(){

    abstract fun usuarioDao() : UsuarioDao

    abstract fun notaDao() : NotaDao

    abstract fun cursoDao() : CursoDao

    abstract fun horarioDao() : HorarioDao

    abstract fun apunteDao() : ApunteDao


}
