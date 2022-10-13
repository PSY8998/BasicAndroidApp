package com.example.basicandroidapp.data.Remote

import com.example.basicandroidapp.data.Posts
import retrofit2.Response
import retrofit2.http.GET

interface PostsApi {
    @GET("/posts")
    suspend fun getPosts() : Response<List<Posts>>
}