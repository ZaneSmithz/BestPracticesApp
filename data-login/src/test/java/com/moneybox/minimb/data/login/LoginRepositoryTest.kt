package com.moneybox.minimb.data.login

import com.moneybox.minimb.data.login.api.LoginApi
import com.moneybox.minimb.data.login.model.LoginRequest
import com.moneybox.minimb.data.login.model.LoginResponse
import com.moneybox.minimb.data.login.model.SessionDataResponse
import com.moneybox.minimb.data.login.model.UserResponse
import com.moneybox.minimb.data.login.repository.LoginRepository
import com.moneybox.minimb.data.login.repository.LoginRepositoryImpl
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import retrofit2.Response

@OptIn(ExperimentalCoroutinesApi::class)
class LoginRepositoryTest {
    private val loginApi: LoginApi = mockk(relaxed = true)

    private lateinit var loginRepository: LoginRepository

    private val loginResponse = LoginResponse(
        session = SessionDataResponse("token"),
        user = UserResponse("1", "first", "second", "email")
    )
    private val loginRequest = LoginRequest(
        email = "email",
        password = "password"
    )
    @Before
    fun setUp() {
        loginRepository = LoginRepositoryImpl(loginApi)
    }
    @Test
    fun `test fetch balance when result is success` () = runTest {
        coEvery { loginApi.fetchLogin(loginRequest.email, loginRequest.password) } returns Response.success(loginResponse)
        val result = loginRepository.fetchLogin(loginRequest)

        assertEquals(loginResponse, result.getOrNull())
        assertEquals(true, result.isSuccess)
        assertEquals(false, result.isFailure)
    }

    @Test
    fun `test fetch balance when result is failure` () = runTest {
        coEvery { loginApi.fetchLogin(loginRequest.email, loginRequest.password) } returns Response.error(400, ResponseBody.create(
            "application/json".toMediaTypeOrNull(),
            "{\"key\":[\"somestuff\"]}"
        ))
        val result = loginRepository.fetchLogin(loginRequest)

        assertEquals(null, result.getOrNull())
        assertEquals(false, result.isSuccess)
        assertEquals(true, result.isFailure)
    }
}