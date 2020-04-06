package com.diegovr17.miu_app_roomdatabase.Tables

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tabla_curso")
class Curso (@PrimaryKey(autoGenerate = true)var id: Int,
               @ColumnInfo(name = "NomCurso") var nombre: String,
               @ColumnInfo(name = "Profesor") var profesor :String,
               @ColumnInfo(name = "#Créditos") var creditos:Int)
