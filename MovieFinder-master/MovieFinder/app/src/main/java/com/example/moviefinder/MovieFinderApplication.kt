package com.example.moviefinder

import android.app.Application
import com.example.moviefinder.data.network.ApiInterface
import com.example.moviefinder.data.network.NetworkConnectionInterceptor
import com.example.moviefinder.data.repositories.HomeRepository
import com.example.moviefinder.data.repositories.MovieDetailRepository
import com.example.moviefinder.data.repositories.MovieListRepository
import com.example.moviefinder.ui.home.HomeViewModelFactory
import com.example.moviefinder.ui.moviedetail.MovieDetailViewModelFactory
import com.example.moviefinder.ui.movielist.MovieListViewModelFactory
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton


class MovieFinderApplication : Application(), KodeinAware
{
    override val kodein = Kodein.lazy{
        import(androidXModule(this@MovieFinderApplication))

        bind() from singleton { NetworkConnectionInterceptor(instance()) }
        bind() from singleton { ApiInterface(instance()) }
        bind() from singleton { HomeRepository(instance()) }
        bind() from provider { HomeViewModelFactory(instance()) }
        bind() from singleton { MovieDetailRepository(instance()) }
        bind() from provider { MovieDetailViewModelFactory(instance()) }
        bind() from singleton { MovieListRepository(instance()) }
        bind() from provider { MovieListViewModelFactory(instance()) }
    }
}