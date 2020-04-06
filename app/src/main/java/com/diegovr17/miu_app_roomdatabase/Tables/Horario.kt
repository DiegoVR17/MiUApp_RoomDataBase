package com.diegovr17.miu_app_roomdatabase.Tables

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tabla_horario")
class Horario (@PrimaryKey(autoGenerate = true)var id:Int,
               @ColumnInfo(name = "NomCurso")var nombCurso:String,
               @ColumnInfo(name = "DiaIni")var diaIni:String,
               @ColumnInfo(name = "DiaFin")var diaFin:String,
               @ColumnInfo(name = "HoraIn")var horaIn:Int,
               @ColumnInfo(name = "HoraFin")var horaFin:Int)