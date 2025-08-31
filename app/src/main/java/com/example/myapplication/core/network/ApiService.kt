package com.example.myapplication.core.network

import com.example.myapplication.feature.authentication.domain.model.AuthToken
import com.example.myapplication.feature.authentication.domain.model.LoginRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiService {

    @POST("auth/login")
    suspend fun login(@Body request: LoginRequest): Response<AuthToken>

    @GET("profile")
    suspend fun getProfile(@Header("Authorization") token: String): Response<Any>

    @GET("dashboard")
    suspend fun getDashboardData(@Header("Authorization") token: String): Response<Any>
}