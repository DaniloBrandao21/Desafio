package com.example.desafio.view.view.repository

import android.content.Context
import com.example.desafio.view.view.model.MoviesModel

class MovieRepository (context: Context) {

    //singleton


    private val moviesDataBase = MoviesDataBase.getDataBase(context).moviesDAO()

    fun save(movie: MoviesModel): Boolean {
        return moviesDataBase.save(movie) > 0
    }

    fun update(movie: MoviesModel): Boolean {

        return moviesDataBase.update(movie) > 0

    }

    fun delete(id: Int) {
        val movie = get(id)
        moviesDataBase.delete(movie)
    }

    fun get(id : Int): MoviesModel {
        return moviesDataBase.get(id)
    }

    fun getAll(): List<MoviesModel> {
        return moviesDataBase.getAll()
    }


}