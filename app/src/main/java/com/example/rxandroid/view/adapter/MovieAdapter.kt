package com.example.rxandroid.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.RecyclerView
import com.example.rxandroid.R
import com.example.rxandroid.databinding.ItemHighRatedMovieLayoutBinding
import com.example.rxandroid.databinding.ItemMovieLayoutBinding
import com.example.rxandroid.model.Movie

class MovieAdapter(val data: ArrayList<Movie>, itemClickListener: (Movie) -> Unit) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        when (viewHolder) {
            is ItemMovieViewHolder -> {
                viewHolder.bind(data[position])
            }
            is ItemHighRatedMovieViewHolder -> {
                viewHolder.bind(data[position])
            }
        }

    }

    override fun getItemViewType(position: Int): Int {
        return if (data[position].vote_average > 7) R.layout.item_high_rated_movie_layout else R.layout.item_movie_layout
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            R.layout.item_movie_layout -> ItemMovieViewHolder(
                ItemMovieLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
            )
            else -> ItemHighRatedMovieViewHolder(
                ItemHighRatedMovieLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
            )
        }

    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun addAll(mFilms: ArrayList<Movie>) {
        val lastSize = data.size
        data.addAll(mFilms)
        notifyItemRangeInserted(lastSize, mFilms.size)
    }

    fun updateSource(mFilms: ArrayList<Movie>) {
        data.clear()
        data.addAll(mFilms)
        notifyDataSetChanged()
    }

    inner class ItemMovieViewHolder(private val viewBinding: ItemMovieLayoutBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {

        fun bind(movie: Movie?) {
            viewBinding.movie = movie
        }
    }

    inner class ItemHighRatedMovieViewHolder(private val viewBinding: ItemHighRatedMovieLayoutBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {

        fun bind(movie: Movie?) {
            viewBinding.movie = movie
        }
    }

}
