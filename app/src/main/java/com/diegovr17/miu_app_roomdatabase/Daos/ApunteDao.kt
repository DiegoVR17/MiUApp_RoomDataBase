package com.diegovr17.miu_app_roomdatabase.Daos

import androidx.room.*
import com.diegovr17.miu_app_roomdatabase.Tables.Apunte

@Dao
interface ApunteDao{
    @Query("SELECT * FROM tabla_apunte")
    fun getAllApuntes():List<Apunte>

    @Query("SELECT * FROM tabla_apunte WHERE Indice =:indice")
    fun findByIndiApunte(indice:Int): Apunte

    @Insert
    fun insertAllApuntes(vararg apuntes: Apunte)

    @Insert
    fun insertApunte(apunte: Apunte)

    @Delete
    fun deleteApunte(apunte: Apunte)

    @Update
    fun updateApunte(apunte: Apunte)
}