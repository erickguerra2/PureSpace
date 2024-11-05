package com.aplication.purespace.navigation

import LoginScreen
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.aplication.purespace.ui.home.view.HomeScreen
import com.aplication.purespace.ui.selectstaff.view.SelectStaffScreen
import com.aplication.purespace.ui.servicer.view.ServicerScreen
import com.aplication.purespace.ui.pago.view.PagoDetallesScreen
import com.aplication.purespace.ui.detalles.view.DetallesServicioScreen
import com.aplication.purespace.ui.history.view.HistoryScreen

@Composable
fun Navigation(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Login){
        composable<Login>{
            LoginScreen{navController.navigate(Home)}
        }
        composable<Home> {
            HomeScreen(
                navigateToSelectStaff = { navController.navigate(SelectStaff) },
                navigateToHistory = { navController.navigate(historial) }
            )
        }
        composable<SelectStaff>{
            SelectStaffScreen{
                navController.navigate(servicer)
            }
        }
        composable<servicer>{
            ServicerScreen{
                navController.navigate(servicio_details)
            }
        }
        composable<pagos>{
            PagoDetallesScreen()

        }
        composable<servicio_details>{
            DetallesServicioScreen{
                navController.navigate(pagos)
            }
        composable<historial>{
            HistoryScreen()
        }

        }
    }
}