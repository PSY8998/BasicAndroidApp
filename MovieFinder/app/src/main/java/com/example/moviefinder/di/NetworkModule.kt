package com.example.moviefinder.di

import android.content.Context
import com.example.moviefinder.BuildConfig
import com.example.moviefinder.di.scopes.ApplicationContext
import com.example.moviefinder.tmdbnetworking.TraktModule
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.ConnectionPool
import okhttp3.Dispatcher
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.LoggingEventListener
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module(includes = [
    TraktModule::class
])
class NetworkModule {
    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor? {
        if (!BuildConfig.DEBUG) {
            return null
        }
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.HEADERS
        }
    }

    @Singleton
    @Provides
    fun provideHttpEventListener(): LoggingEventListener.Factory? {
        if (!BuildConfig.DEBUG) {
            return null
        }
        return LoggingEventListener.Factory()
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(
            httpLoggingInterceptor: HttpLoggingInterceptor?,
            loggingEventListener: LoggingEventListener.Factory?,
            @ApplicationContext context: Context
    ): OkHttpClient {
        return OkHttpClient.Builder().apply {
            if (httpLoggingInterceptor != null) {
                addInterceptor(httpLoggingInterceptor)
            }
            if (loggingEventListener != null) {
                eventListenerFactory(loggingEventListener)
            }
        }
                // Around 4¢ worth of storage in 2020
                .cache(Cache(File(context.cacheDir, "api_cache"), 50 * 1024 * 1024))
                // Adjust the Connection pool to account for historical use of 3 separate clients
                // but reduce the keepAlive to 2 minutes to avoid keeping radio open.
                .connectionPool(ConnectionPool(10, 2, TimeUnit.MINUTES))
                .dispatcher(
                        Dispatcher().apply {
                            // Allow for high number of concurrent image fetches on same host.
                            maxRequestsPerHost = 15
                        }
                )
                .build()
    }
}