package com.ghina0096.assesment1_607062330096_ghinaaniqahyc.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ghina0096.assesment1_607062330096_ghinaaniqahyc.screen.AboutScreen
import com.ghina0096.assesment1_607062330096_ghinaaniqahyc.screen.MainScreen

@Composable
fun SetupNavGraph(navController: NavHostController = rememberNavController()){
    NavHost(
        navController=navController,
        startDestination = Screen.Home.route
    ){
        composable(route = Screen.Home.route){
            MainScreen(navController)
        }
        composable(route = Screen.About.route){
            AboutScreen(navController)
        }
    }
}