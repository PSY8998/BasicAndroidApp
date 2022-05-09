package com.example.moviefinder.di

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module

@Module
internal abstract class ViewModelModule{
    /**
     * Generate provides method for providing ViewModelProvider.Factory
     * Connection between our ViewModelFactory and android ViewModelFactory
     */
    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}
