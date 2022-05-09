package com.example.moviefinder.di.component

import android.content.Context
import com.example.moviefinder.MovieFinderApplication
import com.example.moviefinder.di.scopes.ApplicationContext
import dagger.Module
import dagger.Provides
import dagger.android.DaggerApplication
import javax.inject.Named
import javax.inject.Qualifier
import javax.inject.Singleton



@Module
class AppModule {

    @Singleton
    @Provides
    @ApplicationContext
    fun provideContext(movieFinderApplication: MovieFinderApplication): Context =
            movieFinderApplication.applicationContext

    @Singleton
    @Provides
    @Named("trakt-client-id")
    fun provideTraktClientId(): String = ""

    @Singleton
    @Provides
    @Named("trakt-client-secret")
    fun provideTraktClientSecret(): String = ""

    @Singleton
    @Provides
    @Named("trakt-auth-redirect-uri")
    fun provideTraktAuthRedirectUri(): String = ""
}