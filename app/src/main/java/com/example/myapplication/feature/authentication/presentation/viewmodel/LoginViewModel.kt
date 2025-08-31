package com.example.myapplication.feature.authentication.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.example.myapplication.app.SettingsDataStore
import com.example.myapplication.feature.authentication.domain.usecase.LoginUseCase
import com.example.myapplication.shared.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : BaseViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    private lateinit var layoutDataStore: SettingsDataStore

    fun login() {
        val currentState = _uiState.value

        executeUseCase(
            useCase = { loginUseCase(currentState.email, currentState.password) },
            onSuccess = { authToken ->
                //layoutDataStore = SettingsDataStore(requireContext().dataStore)
                _uiState.value = _uiState.value.copy(
                    isLoginSuccessful = true,
                    errorMessage = ""
                )
            },
            onError = { throwable ->
                _uiState.value = _uiState.value.copy(
                    errorMessage = throwable.message ?: "Erreur de connexion"
                )
            }
        )
    }
}

data class LoginUiState(
    val email: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val errorMessage: String = "",
    val isLoginSuccessful: Boolean = false
)