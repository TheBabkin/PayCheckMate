package com.stefanprvanovic.paycheckmate

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.stefanprvanovic.paycheckmate.ui.screens.Home
import com.stefanprvanovic.paycheckmate.ui.screens.Work
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
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = NavRouts.Home.route) {
        composable(NavRouts.Home.route) {
            Home(viewModel = viewModel, navController = navController)
        }
        composable(NavRouts.Work.route) {
            Work(viewModel = viewModel, navController = navController)
        }
    }
}