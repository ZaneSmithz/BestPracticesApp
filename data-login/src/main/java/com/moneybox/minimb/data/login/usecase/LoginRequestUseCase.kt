package com.moneybox.minimb.data.login.usecase

import com.moneybox.minimb.data.login.model.LoginRequest
import com.moneybox.minimb.data.login.model.LoginResponse
import com.moneybox.minimb.data.login.repository.LoginRepository
import javax.inject.Inject

interface LoginRequestUseCase {
    suspend operator fun invoke(loginRequest: LoginRequest): Result<LoginResponse>
}
internal class LoginRequestUseCaseImpl @Inject constructor(
    private val loginRepository: LoginRepository
): LoginRequestUseCase {
    override suspend fun invoke(loginRequest: LoginRequest): Result<LoginResponse> =
        loginRepository.fetchLogin(loginRequest)

}