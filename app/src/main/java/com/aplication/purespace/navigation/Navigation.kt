package com.aplication.purespace.navigation

import LoginScreen
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
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
import com.aplication.purespace.ui.register.view.RegisterScreen
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

@Composable
fun Navigation(navHostController: NavHostController,auth: FirebaseAuth){

    NavHost(navController = navHostController, startDestination = "Login"){
        composable("Login"){
            LoginScreen(navigateToHome = { navHostController.navigate("Home")},
                navigateToRegister = { navHostController.navigate("Register")},
                auth)
        }
        composable("Register"){
            RegisterScreen(
                navigateToHome = { navHostController.navigate("Home") },
                auth)
        }
        composable("Home") {
            HomeScreen(
                navigateToSelectStaff = { navHostController.navigate("SelectStaff") },
                navigateToHistory = { navHostController.navigate("historial") },
            )
        }
        composable("SelectStaff"){
            SelectStaffScreen{
                navHostController.navigate("servicer")
            }
        }
        composable("servicer"){
            ServicerScreen{
                navHostController.navigate("servicio_details")
            }
        }
        composable("pagos"){
            PagoDetallesScreen(
                navigateToHome = { navHostController.navigate("Home") }
            )

        }
        composable("servicio_details"){
            DetallesServicioScreen{
                navHostController.navigate("pagos")
            }
        composable("historial"){
            HistoryScreen()
        }

        }
    }
}