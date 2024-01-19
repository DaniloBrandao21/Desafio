package com.example.desafio.view.view.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.OnClickListener
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.desafio.R
import com.example.desafio.databinding.ActivityMovieFormBinding
import com.example.desafio.view.view.constants.DataBaseConstants
import com.example.desafio.view.view.model.MoviesModel
import com.example.desafio.view.view.viewModel.MovieFormViewModel

class MovieFormActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMovieFormBinding
    private lateinit var viewModel: MovieFormViewModel

    private var movieId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieFormBinding.inflate(layoutInflater)
        setContentView(binding.root)


        viewModel =ViewModelProvider(this).get(MovieFormViewModel::class.java)

        binding.buttonSave.setOnClickListener(this)
        binding.imageBack.setOnClickListener(this)
        //Implementar o rate

        observe()

        loadData()

    }

    override fun onClick(v: View) {
        if(v.id == R.id.buttonSave){

        val title = binding.editTextTitle.text.toString()
            var rate = 0

            rate = binding.ratingMovie.rating.toInt()

            Log.i("Resultado" , rate.toString())
        val opinion = binding.editTextOpinion.text.toString()

        val model = MoviesModel().apply {
            Log.i("Resultado" , rate.toString())
            this.id = movieId
            this.title = title
            this.rate = rate
            this.opinion = opinion
        }

            viewModel.save(model)
            finish()
        }
        else if(v.id == R.id.image_back){
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun loadData() {
        val bundle = intent.extras

        if (bundle != null) {
            movieId = bundle.getInt(DataBaseConstants.MOVIE.ID)
            viewModel.get(movieId)

        }
    }

    private fun observe() {
        viewModel.movies.observe(this, Observer {
            binding.editTextTitle.setText(it.title)
            binding.editTextOpinion.setText(it.opinion)
            binding.ratingMovie.rating= it.rate.toFloat()
        })
    }
}