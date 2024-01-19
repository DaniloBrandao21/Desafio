package com.example.desafio.view.view.view.adapter

import android.graphics.Movie
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.desafio.databinding.RowMoviesBinding
import com.example.desafio.view.view.model.MoviesModel
import com.example.desafio.view.view.view.listener.OnMovieListener
import com.example.desafio.view.view.view.viewHolder.ItemViewHolder

class ItemAdapter: RecyclerView.Adapter<ItemViewHolder>() {

    private var moviesList:List<MoviesModel> = listOf()
    private lateinit var listener: OnMovieListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {

         val item = RowMoviesBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ItemViewHolder(item, listener)

    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(moviesList[position])
    }

    override fun getItemCount(): Int {
       return moviesList.count()
    }



    fun updatedList(list: List<MoviesModel>){
        moviesList = list

        Log.i("Resultado" , moviesList.get(0).title)
        //Avisa para a RecycleView que recebeu novas informações
        notifyDataSetChanged()
    }

    //Adicionar listener para ouvir os clicks na recycler
    fun attachListener(movieListener: OnMovieListener){
        listener = movieListener

    }

}