package com.moneybox.minimb.data.login.repository

import com.moneybox.minimb.data.login.api.LoginApi
import com.moneybox.minimb.data.login.model.LoginRequest
import com.moneybox.minimb.data.login.model.LoginResponse
import retrofit2.HttpException
import javax.inject.Inject

interface LoginRepository {
    suspend fun fetchLogin(loginRequest: LoginRequest): Result<LoginResponse>

}
internal class LoginRepositoryImpl @Inject constructor(
    private val loginApi: LoginApi
): LoginRepository {
    override suspend fun fetchLogin(loginRequest: LoginRequest): Result<LoginResponse> {
        try {
            val result = loginApi.fetchLogin(loginRequest)
            return if (result.isSuccessful) {
                Result.success(result.body() ?: throw RuntimeException(""))
            } else {
                Result.failure(HttpException(result))
            }
        } catch (e: HttpException) {
            return Result.failure(Exception(""))
        }
    }
}