package com.example.moviefinder.data.repositories

import com.example.moviefinder.R
import com.example.moviefinder.data.models.Movie

object MovieDataSource {

    fun movies() = listOf<Movie>(
        Movie(R.drawable.neymar, "GOne", listOf("Action", "Comedy")),
        Movie(R.drawable.neymar, "One Piece", listOf("Action", "Comedy")),
        Movie(R.drawable.neymar, "Avatar", listOf("Action", "Drama")),
        Movie(R.drawable.neymar, "Ghost", listOf("Action", "Horror"))
    )

    fun genres() = listOf(
        "Action",
        "Comedy",
        "Crime",
        "Drama",
        "Horror"
    )

}