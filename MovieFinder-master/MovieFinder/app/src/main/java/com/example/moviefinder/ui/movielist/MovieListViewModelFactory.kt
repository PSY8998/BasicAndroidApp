package com.example.moviefinder.ui.movielist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.moviefinder.data.repositories.MovieListRepository

@Suppress("UNCHECKED_CAST")
class MovieListViewModelFactory(private val repository: MovieListRepository) : ViewModelProvider.NewInstanceFactory()
{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T
    {
        return MovieListViewModel(repository) as T
    }
}