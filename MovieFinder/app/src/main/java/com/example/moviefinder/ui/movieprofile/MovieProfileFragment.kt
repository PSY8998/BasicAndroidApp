package com.example.moviefinder.ui.movieprofile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.moviefinder.MovieProfileViewModel
import com.example.moviefinder.data.repositories.MovieDataSource
import com.example.moviefinder.databinding.FragmentMoviesBinding


class MovieProfileFragment : Fragment() {

    private val viewModel: MovieProfileViewModel by viewModels()
    private lateinit var binding: FragmentMoviesBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMoviesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val movies = MovieDataSource.movies()
        binding.moviesList.adapter = MoviesAdapter(movies)
        val genres = MovieDataSource.genres()
        binding.genresList.adapter = GenresAdapter(genres)
    }

}

@BindingAdapter("src")
fun setImageResource(imageView: ImageView, resource: Int) {
    imageView.setImageResource(resource)
}






