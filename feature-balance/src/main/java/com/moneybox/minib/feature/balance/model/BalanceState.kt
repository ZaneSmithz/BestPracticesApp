package com.moneybox.minib.feature.balance.model

data class BalanceState(
    val balance: String = "",
    val hasError: Boolean = false,
    val isLoading: Boolean = true
)