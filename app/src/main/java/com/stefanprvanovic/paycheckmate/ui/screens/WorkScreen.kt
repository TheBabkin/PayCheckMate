package com.stefanprvanovic.paycheckmate.ui.screens

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Checkbox
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.stefanprvanovic.paycheckmate.ui.components.CustomEditTextField
import com.stefanprvanovic.paycheckmate.ui.theme.PayCheckMateTheme

@Composable
fun WorkScreen() {
    //Kooperant
    var customer by remember { mutableStateOf("") }
    val onCustomerTextChange = { it: String ->
        customer = it
    }

    //Adresa kooperanta
    var customerAddress by remember { mutableStateOf("") }
    val onCustomerAddressChange = { it: String ->
        customerAddress = it
    }

    //Opis radova
    var workDescription by remember { mutableStateOf("") }
    val onWorkDescriptionChange = { it: String ->
        workDescription = it
    }

    //Cena radova
    var price by remember { mutableStateOf("") }
    val onPriceChange = { it: String ->
        price = it
    }

    //Placeno
    var payed by remember { mutableStateOf(false) }
    val onPayedChange = { it: Boolean ->
        payed = it
    }

    Column(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
    ) {
        CustomEditTextField(
            value = customer,
            onValueChange = onCustomerTextChange,
            label = "Kooperant"
        )

        CustomEditTextField(
            value = customerAddress,
            onValueChange = onCustomerAddressChange,
            label = "Adresa"
        )

        CustomEditTextField(
            value = workDescription,
            onValueChange = onWorkDescriptionChange,
            label = "Radovi"
        )

        CustomEditTextField(
            value = price,
            onValueChange = onPriceChange,
            label = "Cena",
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            singleLine = true
        )

        Row(
            modifier = Modifier.padding(0.dp, 10.dp, 0.dp, 0.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Placeno ", fontWeight = FontWeight.Bold, fontSize = 20.sp)
            Checkbox(checked = payed, onCheckedChange = onPayedChange)
        }
    }
}

@Preview(name = "Light", showBackground = true, showSystemUi = true, uiMode = UI_MODE_NIGHT_NO)
@Preview(name = "Dark", showBackground = true, showSystemUi = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun WorkScreenPreview() {
    PayCheckMateTheme {
        Surface {
            WorkScreen()
        }
    }
}