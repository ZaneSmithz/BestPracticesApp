package com.moneybox.minib.feature.balance

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.moneybox.minib.feature.balance.model.BalanceState

@Composable
internal fun BalanceRoute(
    viewModel: BalanceViewModel = hiltViewModel()
) {
    val collectedState by viewModel.state.collectAsState()
    BalanceScreen(collectedState)

}

@Composable
private fun BalanceScreen(
    state: BalanceState
) {
    Column(
        modifier = Modifier.fillMaxSize().statusBarsPadding(), verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Total Plan Value", fontSize = 30.sp, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(10.dp))
        Text(text = state.balance, fontSize = 15.sp)
    }
}