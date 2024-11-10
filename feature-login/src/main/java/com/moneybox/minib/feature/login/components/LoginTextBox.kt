package com.moneybox.minib.feature.login.components

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.sp
import com.moneybox.minib.feature.login.util.Colours.MBBlue
import com.moneybox.minib.feature.login.util.Colours.MBGreen

@Composable
internal fun LoginTextBox(
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
            focusedLabelColor = MBGreen,
            unfocusedIndicatorColor = MBGreen,
            focusedIndicatorColor = if(isValid) MBBlue else Color.Red ,
            backgroundColor = Color.White,
            textColor = MBBlue
        ),
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Go),
        visualTransformation = visualTransformation,
        label = {
            Text(text = label, fontSize = 12.sp, color = MBBlue)
        }
    )
}