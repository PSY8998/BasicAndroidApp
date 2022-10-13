package com.example.basicandroidapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.basicandroidapp.data.Posts
import kotlinx.coroutines.flow.Flow

@Dao
interface PostDao {

    @Query("SELECT * FROM posts")
    fun getPosts(): Flow<List<Posts>>

    @Insert
    suspend fun insert(posts: Posts)

}