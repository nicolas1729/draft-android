package com.example.myapplication.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserEntity(

    @PrimaryKey
    val id: String,
    val nom: String,
    val prenom: String
)