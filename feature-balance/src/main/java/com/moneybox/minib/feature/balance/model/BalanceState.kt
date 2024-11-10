package com.moneybox.minib.feature.balance.model

data class BalanceState(
    val balance: String = "£10,000.00",
    val hasError: Boolean = false,
    val isLoading: Boolean = true
)