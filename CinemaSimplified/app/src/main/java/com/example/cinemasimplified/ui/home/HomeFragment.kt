package com.example.cinemasimplified.ui.home

import android.annotation.SuppressLint
import android.app.Fragment
import androidx.fragment.app.Fragment
import com.example.cinemasimplified.databinding.FragmentHomeBinding
import org.koin.dsl.module

val homeModule = module {
    factory { HomeFragment() }
}

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    private lateinit var toolbar: CustomToolbarBinding

    private val viewModel: HomeViewModel by viewModel()

    private val upcomingAdapter by lazy {
        UpcomingAdapter(arrayListOf(), object : UpcomingAdapter.OnAdapterListener {
            @SuppressLint("LogNotTimber")
            override fun onClick(articleModel: ResultsItem) {
                val intent = Intent(context, DetailActivity::class.java)
                intent.putExtra("id", articleModel.id)
                intent.putExtra("title", articleModel.title)
                intent.putExtra("original_title", articleModel.originalTitle)
                intent.putExtra(
                    "image",
                    "https://image.tmdb.org/t/p/w185" + articleModel.backdropPath
                )
                intent.putExtra(
                    "poster",
                    "https://image.tmdb.org/t/p/w185" + articleModel.posterPath
                )
                intent.putExtra("overview", articleModel.overview)
                intent.putExtra("rilis", articleModel.releaseDate)
                intent.putExtra("vote", articleModel.voteAverage)
                startActivity(intent)
            }
        })
    }
