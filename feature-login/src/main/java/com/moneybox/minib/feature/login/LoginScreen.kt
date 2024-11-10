package com.moneybox.minib.feature.login

import android.util.Patterns
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun LoginRoute(viewModel: LoginViewModel = hiltViewModel(), navigateTo: () -> Unit) {
    val collectedState by viewModel.state.collectAsState()
    LoginScreen(
        loginState = collectedState,
        updatePassword = viewModel::updatePassword,
        updateEmail = viewModel::updateEmail,
        navigateTo = navigateTo,
        loginClick = viewModel::onLoginClick,
        updateIsValidEmail = viewModel::updateIsValidEmail,
        updateIsValidPassword = viewModel::updateIsValidPassword
    )
}

@Composable
fun LoginScreen(
    loginState: LoginState,
    updateEmail: (String) -> Unit,
    updatePassword: (String) -> Unit,
    navigateTo: () -> Unit,
    updateIsValidPassword: (Boolean) -> Unit,
    updateIsValidEmail: (Boolean) -> Unit,
    loginClick: (() -> Unit) -> Unit
) {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
            .padding(start = 10.dp, top = 80.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        Icon(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            painter = painterResource(R.drawable.moneybox_logo),
            contentDescription = "",
            tint = Color(0xFF00C2B5)
        )
        MBTextBox(
            label = "Email",
            value = loginState.email,
            updateValue = updateEmail,
            visualTransformation = VisualTransformation.None,
            validateField = { text ->
                if (Patterns.EMAIL_ADDRESS.matcher(text).matches() && text.isNotBlank()) {
                    updateIsValidEmail(true)
                }
                else { updateIsValidEmail(false) }
            },
            isValid = loginState.isValidEmail && loginState.email.isNotBlank()
        )
        MBTextBox(
            label = "Password",
            value = loginState.password,
            updateValue = updatePassword,
            visualTransformation = { text ->
                TransformedText(
                    AnnotatedString('\u002A'.toString().repeat(text.text.length)),
                    OffsetMapping.Identity
                )
            },
            validateField = { text ->
                if(text.isNotBlank()) {
                   updateIsValidPassword(true)
                }
                else updateIsValidPassword(false)
            },
            isValid = loginState.isValidPassword && loginState.password.isNotBlank()
            )
        Spacer(Modifier.weight(1f))
        LoginButton(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            onClick = {
                if(loginState.isValidEmail && loginState.isValidPassword) {
                    loginClick.invoke { navigateTo() }
                }
                else {
                    Toast.makeText(context, "Invalid Email or Password", Toast.LENGTH_SHORT).show()
                }
            }
        )
    }
}

@Composable
private fun LoginButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier.then(
            Modifier
                .fillMaxWidth(0.8f)
                .height(50.dp)
        ),
        shape = RoundedCornerShape(10.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color(0xFF00C2B5)
        ),
        onClick = { onClick() }) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            Text("LOG IN", color = Color.White)
        }
    }
}

@Composable
private fun MBTextBox(
    label: String,
    value: String,
    updateValue: (String) -> Unit,
    visualTransformation: VisualTransformation,
    validateField: ((String) -> Unit)? = null,
    isValid: Boolean
) {
    TextField(
        value = value,
        onValueChange = { text ->
            updateValue(text)
            validateField?.let { validation ->
                validation(text)
            }
        },
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.White,
            textColor = if(isValid) Color(0xFF006179) else Color.Red
        ),
        visualTransformation = visualTransformation,
        label = { Text(text = label, fontSize = 12.sp) }
    )
}