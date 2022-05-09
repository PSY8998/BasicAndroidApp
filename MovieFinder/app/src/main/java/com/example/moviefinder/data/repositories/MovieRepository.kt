package com.example.moviefinder.data.repositories

import com.uwetrottmann.trakt5.entities.Movie
import com.uwetrottmann.trakt5.enums.Extended
import com.uwetrottmann.trakt5.services.Movies
import javax.inject.Inject

interface MovieRepository

class MovieRepositoryImpl @Inject constructor(private val movies: Movies):
    MovieRepository {
    fun getMovies(): List<Movie>{
        movies.popular(1, 10, Extended.FULL).execute()
        return emptyList()
    }

}