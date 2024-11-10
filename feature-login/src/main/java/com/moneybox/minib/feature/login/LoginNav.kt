package com.moneybox.minib.feature.login

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

fun NavGraphBuilder.loginScreen(
    navigateTo: () -> Unit,
) {
    composable(route = "login") {
        LoginRoute(navigateTo = navigateTo)
    }
}