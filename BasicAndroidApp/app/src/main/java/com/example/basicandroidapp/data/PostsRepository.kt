package com.example.basicandroidapp.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow

class PostsRepository(
    private val postsDatabase: PostsDatabase
) {

    fun getPosts():Flow<List<Posts>>{
        return postsDatabase.postDao().getPosts()
    }

}
