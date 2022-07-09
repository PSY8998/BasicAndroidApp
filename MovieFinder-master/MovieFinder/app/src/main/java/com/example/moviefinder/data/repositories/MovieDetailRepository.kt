package com.example.moviefinder.data.repositories

import com.example.moviefinder.data.models.MovieDetail
import com.example.moviefinder.data.network.ApiInterface
import com.example.moviefinder.data.network.SafeApiRequest


class MovieDetailRepository(private val api: ApiInterface) : SafeApiRequest()
{
    suspend fun getMovieDetail(title: String, apiKey: String): MovieDetail
    {
        return apiRequest { api.getMovieDetailData(title, apiKey) }
    }
}