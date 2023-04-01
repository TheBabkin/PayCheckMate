package com.stefanprvanovic.paycheckmate.ui.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Checkbox
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.stefanprvanovic.paycheckmate.NavRouts
import com.stefanprvanovic.paycheckmate.Singleton
import com.stefanprvanovic.paycheckmate.ViewModel
import com.stefanprvanovic.paycheckmate.database.Work
import com.stefanprvanovic.paycheckmate.ui.components.CustomEditTextField
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Composable
fun WorkScreen(viewModel: ViewModel, navController: NavController) {

    val context = LocalContext.current

    //Kooperant
    var customer by remember {
        mutableStateOf(
            if (Singleton.work != null) {
                Singleton.work!!.customer
            } else {
                ""
            }
        )
    }

    var isErrorCustomer by remember {
        mutableStateOf(false)
    }

    val onCustomerTextChange = { it: String ->
        customer = it

        isErrorCustomer = false
    }

    //Adresa kooperanta
    var customerAddress by remember {
        mutableStateOf(
            if (Singleton.work != null) {
                Singleton.work!!.customerAddress
            } else {
                ""
            }
        )
    }
    val onCustomerAddressChange = { it: String ->
        customerAddress = it
    }

    //Opis radova
    var workDescription by remember {
        mutableStateOf(
            if (Singleton.work != null) {
                Singleton.work!!.workDescription
            } else {
                ""
            }
        )
    }
    val onWorkDescriptionChange = { it: String ->
        workDescription = it
    }

    //Cena radova
    var price by remember {
        mutableStateOf(
            if (Singleton.work != null) {
                Singleton.work!!.price
            } else {
                ""
            }
        )
    }
    val onPriceChange = { it: String ->
        price = it
    }

    //Placeno
    var payed by remember {
        mutableStateOf(
            if (Singleton.work != null) {
                Singleton.work!!.payed
            } else {
                false
            }
        )
    }
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
            label = "Kooperant",
            isError = isErrorCustomer
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
            Button(onClick = {
                if (customer.isNotEmpty()) {
                    if (Singleton.work != null) {
                        viewModel.updateWork(
                            Work(
                                id = Singleton.work!!.id,
                                customer = customer,
                                customerAddress = customerAddress,
                                workDescription = workDescription,
                                dateTime = Singleton.work!!.dateTime,
                                price = price,
                                payed = payed
                            )
                        )
                    } else {
                        viewModel.insertWork(
                            Work(
                                customer = customer,
                                customerAddress = customerAddress,
                                workDescription = workDescription,
                                dateTime = LocalDateTime.now()
                                    .format(DateTimeFormatter.ofPattern("HH:mm dd.MM.yyyy")),
                                price = price,
                                payed = payed
                            )
                        )
                    }
                    Singleton.work = null
                    navController.navigate(NavRouts.Home.route) {
                        popUpTo(NavRouts.Home.route)
                    }
                } else {
                    isErrorCustomer = true
                    Toast.makeText(context, "Polje ne sme da bude prazno.", Toast.LENGTH_SHORT)
                        .show()
                }
            }) {
                Text(text = "Sacuvaj")
            }
        }
    }
}