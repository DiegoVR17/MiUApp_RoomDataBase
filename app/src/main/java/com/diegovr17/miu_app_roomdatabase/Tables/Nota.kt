package com.diegovr17.miu_app_roomdatabase.Tables

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tabla_nota")
class Nota (@PrimaryKey(autoGenerate = true)var id:Int,
            @ColumnInfo(name = "NombreCurso")var nombreCur:String,
            @ColumnInfo(name = "Indice")var indice:Int,
            @ColumnInfo(name = "Porcentaje")var porcentaje:Float,
            @ColumnInfo(name = "Valor")var valor:Float)