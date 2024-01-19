package com.example.desafio.view.view.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.desafio.view.view.model.MoviesModel
import com.example.desafio.view.view.repository.MovieRepository

class MoviesViewModel(application: Application) : AndroidViewModel(application){

    private val listMovies = MutableLiveData<List<MoviesModel>>()

    private val repository =
        MovieRepository(application.applicationContext)

    val movies : LiveData<List<MoviesModel>> = listMovies

    fun getAll(){
        listMovies.value = repository.getAll()
    }

    fun delete(id: Int){
        repository.delete(id)
    }
}