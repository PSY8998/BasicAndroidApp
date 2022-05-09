package com.example.moviefinder

import com.example.moviefinder.di.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication


class MovieFinderApplication : DaggerApplication() {

    /**
     * AndroidInjector to inject this DaggerComponent to App
     */
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.factory().create(this)
    }

}