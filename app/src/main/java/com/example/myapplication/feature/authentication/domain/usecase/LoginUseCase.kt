package com.example.myapplication.feature.authentication.domain.usecase

import jakarta.inject.Inject
import com.example.myapplication.core.utils.Result
import com.example.myapplication.feature.authentication.domain.model.AuthToken
import com.example.myapplication.feature.authentication.domain.model.LoginRequest
import com.example.myapplication.feature.authentication.domain.repository.UserRepository

class LoginUseCase @Inject constructor(
    private val authRepository: UserRepository
) {
    suspend operator fun invoke(id: String, password: String): Result<AuthToken> {
        if (id.isBlank() || password.isBlank()) {
            return Result.Error(IllegalArgumentException("id et mot de passe requis"))
        }

        return authRepository.login(LoginRequest(id, password))
    }
}