package com.stefanprvanovic.paycheckmate

import android.app.Application
import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
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


    LazyColumn {
        items(allWork) { work ->
            WorkCard(work = work)
        }
    }
}


@Preview(name = "Light", showBackground = true, showSystemUi = true, uiMode = UI_MODE_NIGHT_NO)
@Preview(name = "Dark", showBackground = true, showSystemUi = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun DefaultPreview() {
    val workList: List<Work> = listOf(
        Work(
            customer = "Stefan Prvanovic",
            workDescription = "Daleko je isto a jos dalje je i Srbija. Kada sredimo  ovu zemlju nece je niko prepoznati." +
                    " Ovi prevaranti su daleko ludi. Mada nebo je iznad nas.",
            customerAddress = "Cara Lazara 46, Cicevac",
            dateTime = "10:38, 12.03.2023",
            price = "100",
            payed = false
        ), Work(
            customer = "Stefan Prvanovic",
            workDescription = "Daleko je isto a jos dalje je i Srbija. Kada sredimo  ovu zemlju nece je niko prepoznati." +
                    " Ovi prevaranti su daleko ludi. Mada nebo je iznad nas.",
            customerAddress = "Cara Lazara 46, Cicevac",
            dateTime = "10:38, 12.03.2023",
            price = "100",
            payed = false
        ), Work(
            customer = "Stefan Prvanovic",
            workDescription = "Daleko je isto a jos dalje je i Srbija. Kada sredimo  ovu zemlju nece je niko prepoznati." +
                    " Ovi prevaranti su daleko ludi. Mada nebo je iznad nas.",
            customerAddress = "Cara Lazara 46, Cicevac",
            dateTime = "10:38, 12.03.2023",
            price = "100",
            payed = false
        ), Work(
            customer = "Stefan Prvanovic",
            workDescription = "Daleko je isto a jos dalje je i Srbija. Kada sredimo  ovu zemlju nece je niko prepoznati." +
                    " Ovi prevaranti su daleko ludi. Mada nebo je iznad nas.",
            customerAddress = "Cara Lazara 46, Cicevac",
            dateTime = "10:38, 12.03.2023",
            price = "100",
            payed = false
        ), Work(
            customer = "Stefan Prvanovic",
            workDescription = "Daleko je isto a jos dalje je i Srbija. Kada sredimo  ovu zemlju nece je niko prepoznati." +
                    " Ovi prevaranti su daleko ludi. Mada nebo je iznad nas.",
            customerAddress = "Cara Lazara 46, Cicevac",
            dateTime = "10:38, 12.03.2023",
            price = "100",
            payed = false
        )
    )

    PayCheckMateTheme {
        Surface {
            LazyColumn {
                items(workList) { work ->
                    WorkCard(work = work)
                }
            }
        }
    }
}