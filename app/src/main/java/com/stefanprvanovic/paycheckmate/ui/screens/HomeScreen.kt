package com.stefanprvanovic.paycheckmate.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.stefanprvanovic.paycheckmate.NavRouts
import com.stefanprvanovic.paycheckmate.R
import com.stefanprvanovic.paycheckmate.Singleton
import com.stefanprvanovic.paycheckmate.ViewModel
import com.stefanprvanovic.paycheckmate.ui.components.WorkCard

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeScreen(viewModel: ViewModel, navController: NavController) {
    val allWork by viewModel.allWork.observeAsState(listOf())

    Scaffold(floatingActionButton = {
        ExtendedFloatingActionButton(text = { Text(text = "Dodaj") },
            onClick = { navController.navigate(NavRouts.Work.route) },
            backgroundColor = Color.White,
            contentColor = Color.Black,
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_construction_24),
                    contentDescription = ""
                )
            })
    }) { padding ->
        LazyColumn(modifier = Modifier.padding(padding)) {
            items(allWork) {
                Card(modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(10.dp)
                    .clickable {}, elevation = 10.dp, onClick = {

                }) {
                    Box(modifier = Modifier
                        .fillMaxWidth()
                        .pointerInput(Unit) {
                            detectTapGestures(
                                onTap = { _ ->
                                    Singleton.work = it
                                    navController.navigate(NavRouts.Work.route)
                                },
                                onLongPress = { _ ->
                                    //viewModel.deleteWork(it)
                                })
                        }) {
                        WorkCard(work = it)
                    }
                }
            }
        }
    }
}