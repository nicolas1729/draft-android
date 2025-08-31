package com.example.myapplication.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "profils")
data class ProfilEntity(

    @PrimaryKey
    val id: String,
    val nom: String,
    val prenom: String,
    val fonction: String,
    val motdepasse: String
)