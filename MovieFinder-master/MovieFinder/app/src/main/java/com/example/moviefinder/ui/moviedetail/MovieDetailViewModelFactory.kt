package com.example.moviefinder.ui.moviedetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.moviefinder.data.repositories.MovieDetailRepository

@Suppress("UNCHECKED_CAST")
class MovieDetailViewModelFactory(private val repository: MovieDetailRepository) : ViewModelProvider.NewInstanceFactory()
{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T
    {
        return MovieDetailViewModel(repository) as T
    }
}