package com.example.moviefinder.ui.movieprofile

import androidx.lifecycle.ViewModel
import com.example.moviefinder.di.ViewModelKey
import com.example.moviefinder.di.scopes.FragmentScoped
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap


@Module
internal abstract class MovieProfileFragmentModule {

    /**
     * Generates injector for PostListFragment scoped with Fragment
     */
    @FragmentScoped
    @ContributesAndroidInjector
    internal abstract fun contributesMovieProfileFragment(): MovieProfileFragment

    /**
     * Generates (key, value) pair for ViewModel
     * key : VM::class
     * value : viewModel  ...it is an instance of ViewModel
     */
    @Binds
    @IntoMap
    @ViewModelKey(MovieProfileFragmentViewModel::class)
    abstract fun bindsMovieProfileFragmentViewModel(viewModel: MovieProfileFragmentViewModel): ViewModel
}