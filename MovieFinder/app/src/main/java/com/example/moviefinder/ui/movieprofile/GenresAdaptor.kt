package com.example.moviefinder.ui.movieprofile

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.moviefinder.R
import com.example.moviefinder.data.models.Movie
import com.example.moviefinder.databinding.ItemGenresBinding
import com.google.android.material.chip.Chip
import com.uwetrottmann.tmdb2.entities.Genre

class GenresAdapter(private val genres: List<String>) :
    RecyclerView.Adapter<GenresAdapter.GenreViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreViewHolder {
        val binding: ItemGenresBinding = ItemGenresBinding.inflate(LayoutInflater.from(parent.context), parent, false )
        return GenreViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GenreViewHolder, position: Int) {
        holder.bind(genres[position])
    }

    override fun getItemCount(): Int {
        return genres.size
    }

    class GenreViewHolder(private val binding: ItemGenresBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.chip.setOnClickListener {

            }
        }

        fun bind(genre: String) {
            binding.genreName = genre
            binding.executePendingBindings()
        }
    }
}