package com.diegovr17.miu_app_roomdatabase.Tables

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tabla_usuario")
class Usuario (@PrimaryKey(autoGenerate = true)var id: Int,
               @ColumnInfo(name = "Nombre") var nombre: String,
               @ColumnInfo(name = "Apellido") var apellido:String,
               @ColumnInfo(name = "E-mail")var correo:String,
               @ColumnInfo(name = "Password") var password:String,
               @ColumnInfo(name = "CarreraU") var carrera:String)