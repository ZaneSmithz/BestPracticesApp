package com.moneybox.minib.feature.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moneybox.minib.core.data.store.EncryptedPrefManager
import com.moneybox.minib.core.ui.MainDispatcher
import com.moneybox.minimb.data.login.model.LoginRequest
import com.moneybox.minimb.data.login.usecase.LoginRequestUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class LoginState(
    val email: String = "",
    val password: String = "",
    val isValidEmail: Boolean = false,
    val isValidPassword: Boolean = false,
)
@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginRequestUseCase: LoginRequestUseCase,
    private val encryptedPrefManager: EncryptedPrefManager,
    @MainDispatcher private val dispatcher: CoroutineDispatcher
): ViewModel() {
    private val _state = MutableStateFlow(LoginState())
    val state = _state.asStateFlow()

    fun updateEmail(email: String){
        _state.update { state -> state.copy(email = email) }
    }
    fun updatePassword(password: String){
        _state.update { state -> state.copy(password = password) }
    }

    fun onLoginClick(navigationCallback: () -> Unit) {
        viewModelScope.launch(dispatcher) {
            val result = loginRequestUseCase.invoke(
                LoginRequest(
                    email = _state.value.email,
                    password = _state.value.password,
                )
            )
            when(result.isSuccess) {
                true -> {
                    result.getOrNull()?.session?.bearerToken?.let { token ->
                        encryptedPrefManager.saveToken(token)
                    }
                    navigationCallback()
                }
                false -> navigationCallback() // as API returning 400 currently
            }
        }
    }

    fun updateIsValidEmail(isValidEmail: Boolean) {
        _state.update { state -> state.copy(isValidEmail = isValidEmail) }
    }

    fun updateIsValidPassword(isValidPassword: Boolean) {
        _state.update { state -> state.copy(isValidPassword = isValidPassword) }
    }
}