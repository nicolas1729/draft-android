package com.example.myapplication.feature.profil.data.mapper

import com.example.myapplication.core.database.entity.UserEntity
import com.example.myapplication.feature.profil.data.dto.ProfilDto

class ProfilMapper {


    fun mapProfil(from: UserEntity): ProfilDto = ProfilDto(
        id = from.id,
        nom = from.nom,
        prenom = from.prenom
    )
}