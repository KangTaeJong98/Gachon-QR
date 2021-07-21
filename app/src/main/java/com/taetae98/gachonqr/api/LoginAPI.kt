package com.taetae98.gachonqr.api

import com.taetae98.gachonqr.dto.request.LoginRequest
import com.taetae98.gachonqr.dto.response.LoginResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginAPI {
    companion object {
        private const val BASE_URL = "http://smart.gachon.ac.kr:8080"

        private var instance: LoginAPI? = null

        fun getInstance(): LoginAPI {
            return instance ?: synchronized(this) {
                instance ?: Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(LoginAPI::class.java).also {
                        instance = it
                    }
            }
        }
    }

    @POST("/WebJSON")
    suspend fun login(
        @Body
        request: LoginRequest
    ): LoginResponse
}