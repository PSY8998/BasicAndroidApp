package com.example.basicandroidapp.data.Remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object PostsRemoteDataSource {
    private const val baseUrl = "https://jsonplaceholder.typicode.com/"

    private val retrofitInstance: Retrofit = Retrofit.Builder().baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val postsService = retrofitInstance.create(PostsApi::class.java)
}