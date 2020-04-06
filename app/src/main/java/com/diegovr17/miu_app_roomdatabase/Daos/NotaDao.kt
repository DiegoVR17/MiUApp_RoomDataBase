package com.diegovr17.miu_app_roomdatabase.Daos

import androidx.room.*
import com.diegovr17.miu_app_roomdatabase.Tables.Nota

@Dao
interface NotaDao{
    @Query("SELECT * FROM tabla_nota")
    fun getAllNotas():List<Nota>

    @Query("SELECT * FROM tabla_nota WHERE nombreCurso =:nombreCurso")
    fun findByNota(nombreCurso:String): Nota

    @Insert
    fun insertAllNotas(vararg notas: Nota)

    @Insert
    fun insertNota(nota: Nota)

    @Delete
    fun deleteNota(nota: Nota)

    @Update
    fun updateNota(nota: Nota)
}