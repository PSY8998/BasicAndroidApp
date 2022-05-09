package com.example.moviefinder.tmdbnetworking

import com.uwetrottmann.trakt5.TraktV2
import com.uwetrottmann.trakt5.services.Movies
import com.uwetrottmann.trakt5.services.Users
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module(
    includes = [TraktServiceModule::class]
)
class TraktModule {

    @Singleton
    @Provides
    fun provideTrakt(
            client: OkHttpClient,
            @Named("trakt-client-id") clientId: String,
            @Named("trakt-client-secret") clientSecret: String,
            @Named("trakt-auth-redirect-uri") redirectUri: String
    ): TraktV2 = object : TraktV2(clientId, clientSecret, redirectUri) {
        override fun okHttpClient(): OkHttpClient {
            return client.newBuilder().apply {
                setOkHttpClientDefaults(this)
                connectTimeout(20, TimeUnit.SECONDS)
                readTimeout(20, TimeUnit.SECONDS)
                writeTimeout(20, TimeUnit.SECONDS)

            }.build()

        }
    }
}

@Module
class TraktServiceModule{

    @Provides
    fun provideTraktMovieServices(traktV2: TraktV2): Movies = traktV2.movies()
}

