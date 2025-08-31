package com.example.myapplication.feature.authentication.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.LiveData
import com.example.myapplication.core.database.AppDatabase
import com.example.myapplication.core.database.entity.UserEntity
import com.example.myapplication.core.utils.Result
import com.example.myapplication.feature.authentication.domain.model.AuthToken
import com.example.myapplication.feature.authentication.domain.model.LoginRequest
import com.example.myapplication.feature.authentication.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val database: AppDatabase,
    private val dataStore: DataStore<Preferences>
): UserRepository {

    companion object {
        private val USERNAME_KEY = stringPreferencesKey("username")
    }

    val usernameFlow: Flow<String?> = dataStore.data
        .map { prefs ->
            prefs[USERNAME_KEY]
        }

    suspend fun saveUsername(username: String) {
        dataStore.edit { prefs ->
            prefs[USERNAME_KEY] = username
        }
    }

    override suspend fun login(request: LoginRequest): Result<AuthToken> {

        val user: LiveData<UserEntity?> = database.userDao().getUser(request.id)

        val authToken = AuthToken("token", request.id);
        return Result.Success(authToken)
    }

    override suspend fun logout(): Result<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun getCurrentUser(): Result<AuthToken?> {
        TODO("Not yet implemented")
    }


}