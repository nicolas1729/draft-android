package com.example.myapplication.feature.profil.data.repository

import androidx.lifecycle.LiveData
import com.example.myapplication.core.database.AppDatabase
import com.example.myapplication.core.database.entity.ProfilEntity
import com.example.myapplication.core.utils.Result
import com.example.myapplication.feature.profil.data.dto.ProfilDto
import com.example.myapplication.feature.profil.domain.repository.ProfilRepository
import com.example.myapplication.shared.domain.model.User
import javax.inject.Inject

class ProfilRepositoryImpl @Inject constructor(
    private val database: AppDatabase
): ProfilRepository {

    override fun getProfil(user: User): Result<ProfilDto> {

        val profilEntity: LiveData<ProfilEntity?> = database.profilDao().getProfil(user.id)
        val profil = ProfilDto(
            profilEntity.value?.id ?: "",
            profilEntity.value?.nom ?: "",
            profilEntity.value?.prenom ?: ""
        )
        return Result.Success(profil)
    }



}