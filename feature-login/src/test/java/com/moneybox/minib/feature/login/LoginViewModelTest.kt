package com.moneybox.minib.feature.login

import app.cash.turbine.test
import com.moneybox.minib.core.data.store.EncryptedPrefManager
import com.moneybox.minimb.data.login.model.LoginResponse
import com.moneybox.minimb.data.login.model.SessionDataResponse
import com.moneybox.minimb.data.login.model.UserResponse
import com.moneybox.minimb.data.login.usecase.LoginRequestUseCase
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class LoginViewModelTest {
    private val loginRequestUseCase: LoginRequestUseCase = mockk(relaxed = true)
    private val encryptedPrefManager: EncryptedPrefManager = mockk(relaxed = true)
    private lateinit var loginViewModel: LoginViewModel

    private val loginResponse = LoginResponse(
        session = SessionDataResponse("token"),
        user = UserResponse("1", "first", "second", "email")
    )
    @Before
    fun setUp() {
        loginViewModel = LoginViewModel(
            loginRequestUseCase = loginRequestUseCase,
            encryptedPrefManager = encryptedPrefManager,
            dispatcher = UnconfinedTestDispatcher()
        )
    }

    @Test
    fun `test init state`() = runTest {
        loginViewModel.state.test {
            assertEquals(awaitItem(), LoginState())
        }
    }

    @Test
    fun `test onLoginClick when result returns success`() = runTest {
        coEvery { loginRequestUseCase.invoke(any()) } returns Result.success(loginResponse)
        val onClick = mockk<() -> Unit>()
        loginViewModel.onLoginClick { onClick() }

        verify(exactly = 1) { onClick() }
        verify { encryptedPrefManager.saveToken(loginResponse.session.bearerToken) }
    }

    @Test
    fun `test onLoginClick when result returns failure`() = runTest {
        coEvery { loginRequestUseCase.invoke(any()) } returns Result.failure(Exception(""))
        val onClick = mockk<() -> Unit>()
        loginViewModel.onLoginClick { onClick() }

        verify(exactly = 1) { onClick() }
        verify(inverse = true) { encryptedPrefManager.saveToken(loginResponse.session.bearerToken) }
    }

    @Test
    fun `test state when login value has been updated`() = runTest {
        loginViewModel.updateEmail("changedEmail")
        loginViewModel.state.test {
            assertEquals(awaitItem().email, "changedEmail")
        }
    }
}