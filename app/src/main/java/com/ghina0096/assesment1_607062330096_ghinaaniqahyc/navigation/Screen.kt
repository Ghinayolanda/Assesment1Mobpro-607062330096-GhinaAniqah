package com.ghina0096.assesment1_607062330096_ghinaaniqahyc.navigation

sealed class Screen(val route: String) {
    data object Home: Screen("mainScreen")
    data object About: Screen("aboutScreen")
}