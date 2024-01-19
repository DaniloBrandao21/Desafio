package com.example.desafio.view.view.view.viewHolder

import android.app.AlertDialog
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.desafio.databinding.RowMoviesBinding
import com.example.desafio.view.view.model.MoviesModel
import com.example.desafio.view.view.view.listener.OnMovieListener

class ItemViewHolder(private val bind: RowMoviesBinding, private val listener: OnMovieListener) :
    RecyclerView.ViewHolder(bind.root) {

    fun bind(movie: MoviesModel) {

        bind.textTitle.text = movie.title

        bind.textTitle.setOnClickListener {
            listener.onClick(movie.id)
        }

        bind.textTitle.setOnLongClickListener(View.OnLongClickListener {

            AlertDialog.Builder(itemView.context)
                .setTitle("Remoção de Filme")
                .setMessage("Tem certeza que deseja excluir esse Filme?")
                .setPositiveButton("Sim"
                ) { dialog, which -> listener.onDelete(movie.id) }
                .setNegativeButton("Não", null )
                .create()
                .show()


            true
        })



    }
}