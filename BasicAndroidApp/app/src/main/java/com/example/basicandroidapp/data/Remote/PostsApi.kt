package com.example.basicandroidapp.data.Remote

import com.example.basicandroidapp.data.Posts
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST

interface PostsApi {

    @GET("users?page=2")
    suspend fun getPosts() : Response<List<POST>>
}