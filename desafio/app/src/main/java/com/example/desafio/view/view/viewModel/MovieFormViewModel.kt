package com.example.desafio.view.view.viewModel

import android.app.Application
import android.graphics.Movie
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.desafio.view.view.model.MoviesModel
import com.example.desafio.view.view.repository.MovieRepository
import kotlin.math.log

class MovieFormViewModel(application: Application) : AndroidViewModel(application) {

    private val moviesRepository = MovieRepository(application.applicationContext)
    private val moviesModel = MutableLiveData<MoviesModel>()
    val movies: LiveData<MoviesModel> = moviesModel

    private val _saveMovie = MutableLiveData<String>()
    val saveMovie: LiveData<String> = _saveMovie

    fun save(movie: MoviesModel) {
//        val movie = MoviesModel().apply {
//            this.id = id
//            this.title = title
//            this.rate = rate
//            this.opinion = opinion
//        }

        if (movie.id == 0 || movie.id == null) {
            _saveMovie.value = moviesRepository.save(movie).toString()
        } else {
            _saveMovie.value = moviesRepository.update(movie).toString()
        }


    }

    fun get(id: Int) {
        moviesModel.value = moviesRepository.get(id)
    }

}