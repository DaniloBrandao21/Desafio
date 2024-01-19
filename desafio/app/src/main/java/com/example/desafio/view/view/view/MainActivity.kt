package com.example.desafio.view.view.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isInvisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.desafio.R
import com.example.desafio.databinding.ActivityMainBinding
import com.example.desafio.view.view.constants.DataBaseConstants
import com.example.desafio.view.view.view.adapter.ItemAdapter
import com.example.desafio.view.view.view.listener.OnMovieListener
import com.example.desafio.view.view.viewModel.MoviesViewModel

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding
    private var _binding: ActivityMainBinding? = null
    lateinit var viewModel: MoviesViewModel
    private val adapter = ItemAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        viewModel = ViewModelProvider(this).get(MoviesViewModel::class.java)

        binding.buttonAdd.setOnClickListener(this)


        // Layout
        binding.recycleMovies.layoutManager = LinearLayoutManager(this)


        // Adapter
        binding.recycleMovies.adapter = adapter


               val listener = object : OnMovieListener{
                   override fun onClick(id: Int) {
                       Log.i("Resultado click", "tentando clicar")
                       val intent = Intent(applicationContext, MovieFormActivity::class.java)
                       val bundle = Bundle()
                       bundle.putInt(DataBaseConstants.MOVIE.ID, id)
                       intent.putExtras(bundle)
                       startActivity(intent)
                   }

                   override fun onDelete(movie: Int) {
                       Log.i("Resultado delete", "tentando deletar")
                       viewModel.delete(movie)
                       viewModel.getAll()
                   }


               }

        adapter.attachListener(listener)


        observe()
    }

    override fun onClick(v: View) {
        if (v.id == R.id.button_add){
            val intent = Intent(this, MovieFormActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume(){
        super.onResume()
        viewModel.getAll()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun observe() {

        viewModel.movies.observe(this, Observer {
           if (it.size > 0){
               adapter.updatedList(it)
               binding.textViewNoMovies.isInvisible = true
               binding.recycleMovies.isInvisible = false
           }else{
               binding.textViewNoMovies.isInvisible = false
               binding.recycleMovies.isInvisible = true
           }
        })
    }

}