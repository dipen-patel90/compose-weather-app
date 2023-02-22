package com.compose.weather.view.common

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.compose.weather.common.getMutableStateValue
import com.compose.weather.common.setMutableStateValue
import com.compose.weather.view.login.UITextField

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OutlinedTextFieldWithError(uiTextField: UITextField, label: Int, isPassword: Boolean = false) {
    OutlinedTextField(
        label = { Text(stringResource(id = label)) },
        value = getMutableStateValue(state = uiTextField.state),
        onValueChange = {
            setMutableStateValue(state = uiTextField.state, value = it)
        },
        isError = uiTextField.showError,
        visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None,
        keyboardOptions = if (isPassword) KeyboardOptions(keyboardType = KeyboardType.Password) else KeyboardOptions.Default,
    )

    if (uiTextField.showError) {
        Text(
            text = stringResource(id = uiTextField.errorMessage),
            color = MaterialTheme.colorScheme.error
        )
    }
}