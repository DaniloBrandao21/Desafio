package com.example.desafio.view.view.repository

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.desafio.view.view.model.MoviesModel

@Dao
interface MoviesDAO {

    @Insert
    fun save(movie : MoviesModel) : Long

    @Update
    fun update(movie : MoviesModel) : Int

    @Delete
    fun delete(movie : MoviesModel)

    @Query("SELECT * FROM Movies WHERE id = :id")
    fun get(id: Int) : MoviesModel

    @Query("SELECT * FROM Movies")
    fun getAll() : List<MoviesModel>

}