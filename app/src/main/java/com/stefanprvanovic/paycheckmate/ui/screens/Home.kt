package com.stefanprvanovic.paycheckmate.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExtendedFloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.stefanprvanovic.paycheckmate.NavRouts
import com.stefanprvanovic.paycheckmate.R
import com.stefanprvanovic.paycheckmate.ViewModel
import com.stefanprvanovic.paycheckmate.ui.components.WorkCard

@Composable
fun Home(viewModel: ViewModel, navController: NavController) {
    val allWork by viewModel.allWork.observeAsState(listOf())

    Scaffold(floatingActionButton = {
        ExtendedFloatingActionButton(
            text = { Text(text = "Dodaj") },
            onClick = { navController.navigate(NavRouts.Work.route) },
            backgroundColor = Color.White,
            contentColor = Color.Black,
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_construction_24),
                    contentDescription = ""
                )
            }
        )
    }) { padding ->
        LazyColumn(modifier = Modifier.padding(padding)) {
            items(allWork) {
                WorkCard(work = it)
            }
        }
    }
}