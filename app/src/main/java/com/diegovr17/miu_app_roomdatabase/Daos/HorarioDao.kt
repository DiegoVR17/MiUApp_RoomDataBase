package com.diegovr17.miu_app_roomdatabase.Daos

import androidx.room.*
import com.diegovr17.miu_app_roomdatabase.Tables.Horario

@Dao
interface HorarioDao{
    @Query("SELECT * FROM tabla_horario")
    fun getAllHoraio():List<Horario>

    @Query("SELECT * FROM tabla_horario WHERE NomCurso =:nombcurso")
    fun findByCurso(nombcurso:String): Horario

    @Insert
    fun insertAllCursos(vararg horarios: Horario)

    @Insert
    fun insertCurso(horario: Horario)

    @Delete
    fun deleteCurso(horario: Horario)

    @Update
    fun updateCurso(horario: Horario)
}