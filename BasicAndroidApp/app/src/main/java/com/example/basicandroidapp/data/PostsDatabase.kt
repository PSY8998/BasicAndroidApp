package com.example.basicandroidapp.data

import androidx.room.Database
import androidx.room.RoomDatabase

interface PostsDatabase {
    fun postDao(): PostDao
}

@Database(entities = [Posts:: class], version = 1)
abstract class PostDatabase() : RoomDatabase(), PostsDatabase
