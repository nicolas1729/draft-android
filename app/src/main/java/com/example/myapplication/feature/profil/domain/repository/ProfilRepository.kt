package com.example.myapplication.feature.profil.domain.repository

import com.example.myapplication.core.utils.Result
import com.example.myapplication.feature.authentication.domain.model.AuthToken
import com.example.myapplication.feature.authentication.domain.model.LoginRequest
import com.example.myapplication.feature.profil.data.dto.ProfilDto
import com.example.myapplication.shared.domain.model.User

interface ProfilRepository {

    fun getProfil(user: User): Result<ProfilDto>
}