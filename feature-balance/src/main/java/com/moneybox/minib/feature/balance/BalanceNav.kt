package com.moneybox.minib.feature.balance

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

fun NavGraphBuilder.balanceScreen() {
    composable(route = "balance") {
        BalanceRoute()
    }
}