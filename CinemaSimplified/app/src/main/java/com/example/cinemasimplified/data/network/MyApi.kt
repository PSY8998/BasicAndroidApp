package com.example.cinemasimplified.data.network

import com.example.cinemasimplified.data.model.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface MyApi {
    @GET("discover/movie")
    suspend fun getMovie(
        @Query("page") page: Int,
        @Query("api_key") api_key: String,
        @Query("with_genres") with_genres: Int,
        @Header("language") language: String
    ): MovieResponse
}