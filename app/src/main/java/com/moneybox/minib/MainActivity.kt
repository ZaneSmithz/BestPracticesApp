package com.moneybox.minib

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Surface
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.moneybox.minib.feature.balance.balanceScreen
import com.moneybox.minib.feature.login.loginScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navHostController = rememberNavController()
            Surface(color = Color.White) {
                NavHost(
                    navController = navHostController,
                    startDestination = "login"
                ) {
                    loginScreen(navigateTo = {navHostController.navigate("balance")})
                    balanceScreen()
                }
            }
        }
    }

}