package com.example.basicandroidapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "posts")
data class Posts(
    val userId: Int,
    @PrimaryKey
    val id: Int,
    val title: String,
    val body: String
)