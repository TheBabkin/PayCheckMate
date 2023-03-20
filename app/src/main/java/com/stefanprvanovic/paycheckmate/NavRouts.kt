package com.stefanprvanovic.paycheckmate

sealed class NavRouts(val route: String) {
    object Home : NavRouts("home")
    object Work : NavRouts("work")
}