package com.example.myapplication.shared.presentation.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        _error.value = throwable.message
        _isLoading.value = false
    }

    protected fun <T> executeUseCase(
        useCase: suspend () -> Result<T>,
        onSuccess: (T) -> Unit = {},
        onError: (Throwable) -> Unit = {}
    ) {
        viewModelScope.launch(exceptionHandler) {
            _isLoading.value = true
            _error.value = null

            when (val result = useCase()) {
                is Result.Success -> {
                    onSuccess(result.data)
                    _isLoading.value = false
                }
                is Result.Error -> {
                    onError(result.exception)
                    _error.value = result.exception.message
                    _isLoading.value = false
                }
                is Result.Loading -> {
                    _isLoading.value = true
                }
            }
        }
    }

    fun clearError() {
        _error.value = null
    }
}