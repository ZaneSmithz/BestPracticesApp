package com.moneybox.minimb.data.login.api

import com.moneybox.minimb.data.login.model.LoginRequest
import com.moneybox.minimb.data.login.model.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface LoginApi {
    @POST("users/login")
    suspend fun fetchLogin(@Body loginRequest: LoginRequest): Response<LoginResponse>
}