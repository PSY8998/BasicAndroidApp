package com.example.basicandroidapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.basicandroidapp.data.Posts
import com.example.basicandroidapp.data.local.PostDao


@Database(entities = [Posts:: class], version = 1)
abstract class PostsDatabase : RoomDatabase(){
    abstract fun postDao(): PostDao
}
