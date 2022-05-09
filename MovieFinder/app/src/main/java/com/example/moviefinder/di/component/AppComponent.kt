package com.example.moviefinder.di.component

import androidx.core.view.KeyEventDispatcher
import com.example.moviefinder.MovieFinderApplication
import com.example.moviefinder.di.NetworkModule
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
        modules = [
            AndroidSupportInjectionModule::class,
            AppModule::class,
            NetworkModule::class
        ]
)
interface AppComponent : AndroidInjector<MovieFinderApplication> {

    @Component.Factory
    abstract class Builder : AndroidInjector.Factory<MovieFinderApplication>

}