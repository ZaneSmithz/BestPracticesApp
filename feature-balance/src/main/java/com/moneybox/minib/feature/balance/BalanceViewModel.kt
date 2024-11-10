package com.moneybox.minib.feature.balance

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moneybox.minib.core.data.store.EncryptedPrefManager
import com.moneybox.minib.core.ui.IoDispatcher
import com.moneybox.minib.data.balance.usecase.BalanceRequestUseCase
import com.moneybox.minib.feature.balance.model.BalanceState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.text.DecimalFormat
import javax.inject.Inject

@HiltViewModel
internal class BalanceViewModel @Inject constructor(
    private val balanceRequestUseCase: BalanceRequestUseCase,
    private val encryptedPrefManager: EncryptedPrefManager,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : ViewModel() {
    private val _state = MutableStateFlow(BalanceState())
    val state = _state.asStateFlow()

    init {
        retrieveBalance()
    }

    private fun retrieveBalance() {
        viewModelScope.launch(dispatcher) {
            encryptedPrefManager.getToken()?.let { token ->
                val result = balanceRequestUseCase.invoke(token)
                when (result.isSuccess) {
                    true -> {
                        val transformedPlanValue = result.getOrNull()?.totalPlanValue.toString().format(
                            DecimalFormat("###,###.##")
                        )
                        _state.update { state -> state.copy(balance =  "£$transformedPlanValue") }
                        _state.update { state -> state.copy(isLoading =  false, hasError = false) }
                    }
                    false -> _state.update { state -> state.copy(hasError =  true, isLoading = false) }

                }
            }
        }
    }
}