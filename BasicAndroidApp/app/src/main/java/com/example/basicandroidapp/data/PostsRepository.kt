package com.example.basicandroidapp.data

import com.example.basicandroidapp.data.local.PostsDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow

class PostsRepository(
    private val postsDatabase: PostsDatabase
) {

    fun getPosts():Flow<List<Posts>>{
        return postsDatabase.postDao().getPosts()
    }

}
