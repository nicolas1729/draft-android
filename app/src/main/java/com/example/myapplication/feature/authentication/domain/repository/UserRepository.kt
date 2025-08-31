package com.example.myapplication.feature.authentication.domain.repository

import com.example.myapplication.core.utils.Result
import com.example.myapplication.feature.authentication.domain.model.AuthToken
import com.example.myapplication.feature.authentication.domain.model.LoginRequest

interface UserRepository {
    suspend fun login(request: LoginRequest): Result<AuthToken> {
        val authToken = AuthToken("token", request.id);
        return Result.Success(authToken)
    }
    suspend fun logout(): Result<Unit>
    suspend fun getCurrentUser(): Result<AuthToken?>
}