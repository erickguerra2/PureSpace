package com.aplication.purespace.navigation

import LoginScreen
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.aplication.purespace.ui.home.view.HomeScreen
import com.aplication.purespace.ui.selectstaff.view.SelectStaffScreen

@Composable
fun Navigation(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Login){
        composable<Login>{
            LoginScreen{navController.navigate(Home)}
        }
        composable<Home>{
            HomeScreen {
                navController.navigate(SelectStaff)
            }
        }
        composable<SelectStaff>{
            SelectStaffScreen()
        }
    }
}