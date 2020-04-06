package com.diegovr17.miu_app_roomdatabase.Tables

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tabla_apunte")
class Apunte (@PrimaryKey(autoGenerate = true)var id:Int,
              @ColumnInfo(name = "Titulo")var titulo:String,
              @ColumnInfo(name = "Descripción")var descripcion:String,
              @ColumnInfo(name = "Indice")var indice:Int)