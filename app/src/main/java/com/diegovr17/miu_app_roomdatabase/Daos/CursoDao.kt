package com.diegovr17.miu_app_roomdatabase.Daos

import androidx.room.*
import com.diegovr17.miu_app_roomdatabase.Tables.Curso


@Dao
interface CursoDao{
    @Query("SELECT * FROM tabla_curso")
    fun getAllCursos():List<Curso>

    @Query("SELECT * FROM tabla_curso WHERE nomCurso LIKE :nombre")
    fun findByCurso(nombre:String): Curso

    @Insert
    fun insertAllCursos(vararg materias: Curso)

    @Insert
    fun insertCurso(materia: Curso)

    @Delete
    fun deleteCurso(materia: Curso)

    @Update
    fun updateCurso(materia: Curso)
}