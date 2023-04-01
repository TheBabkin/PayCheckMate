package com.stefanprvanovic.paycheckmate.ui.components

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.stefanprvanovic.paycheckmate.ui.theme.PayCheckMateTheme

@Composable
fun CustomEditTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    keyboardOptions: KeyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
    modifier: Modifier = Modifier.fillMaxWidth(),
    singleLine: Boolean = false,
    isError: Boolean = false
) {
    OutlinedTextField(
        value = value,
        onValueChange = { onValueChange(it) },
        label = { Text(text = label) },
        modifier = modifier,
        singleLine = singleLine,
        keyboardOptions = keyboardOptions,
        isError = isError
    )
}

@Preview(name = "Light mode", showBackground = true, uiMode = UI_MODE_NIGHT_NO)
@Preview(name = "Dark mode", showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun CustomEditTextFieldPreview() {
    PayCheckMateTheme {
        Surface {
            CustomEditTextField(value = "", onValueChange = {}, label = "Korisnik")
        }
    }
}