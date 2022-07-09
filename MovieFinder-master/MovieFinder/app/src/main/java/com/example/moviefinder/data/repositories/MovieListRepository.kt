package com.example.moviefinder.data.repositories

import com.example.moviefinder.data.models.SearchResults
import com.example.moviefinder.data.network.ApiInterface
import com.example.moviefinder.data.network.SafeApiRequest

class MovieListRepository(private val api: ApiInterface) : SafeApiRequest()
{
    suspend fun getMovies(searchTitle: String, apiKey: String, pageIndex: Int): SearchResults
    {
        return apiRequest{ api.getSearchResultData(searchTitle, apiKey, pageIndex) }
    }
}