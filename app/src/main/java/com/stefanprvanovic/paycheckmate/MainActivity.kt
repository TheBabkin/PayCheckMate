package com.stefanprvanovic.paycheckmate

import android.app.Application
import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import com.stefanprvanovic.paycheckmate.database.Work
import com.stefanprvanovic.paycheckmate.ui.components.WorkCard
import com.stefanprvanovic.paycheckmate.ui.theme.PayCheckMateTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PayCheckMateTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val owner = LocalViewModelStoreOwner.current

                    owner?.let {
                        val viewModel: ViewModel = viewModel(
                            it, "ViewModel", ViewModelFactory(
                                LocalContext.current.applicationContext as Application
                            )
                        )

                        MainScreen(viewModel)
                    }
                }
            }
        }
    }

    class ViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
        override fun <T : androidx.lifecycle.ViewModel> create(modelClass: Class<T>): T {
            return ViewModel(application) as T
        }
    }
}

@Composable
fun MainScreen(viewModel: ViewModel) {

    val allWork by viewModel.allWork.observeAsState(listOf())

    Scaffold(floatingActionButton = {
        ExtendedFloatingActionButton(
            text = { Text(text = "Dodaj") },
            onClick = { /*TODO*/ },
            backgroundColor = Color.White,
            contentColor = Color.Black,
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_construction_24),
                    contentDescription = ""
                )
            }
        )
    }) {
        LazyColumn(modifier = Modifier.padding(it)) {
            items(allWork) {
                WorkCard(work = it)
            }
        }
    }
}


@Preview(name = "Light", showBackground = true, showSystemUi = true, uiMode = UI_MODE_NIGHT_NO)
@Composable
fun DefaultPreview() {
    PayCheckMateTheme {

    }
}