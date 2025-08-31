package com.example.myapplication.feature.profil.data.dto

data class ProfilDto(
    val id: String,
    val nom: String,
    val prenom: String,
    val fonction: String = "",
    val motdepasse: String = ""
)