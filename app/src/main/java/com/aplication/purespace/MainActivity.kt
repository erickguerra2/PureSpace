package com.aplication.purespace

import LoginScreen
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.aplication.purespace.navigation.Navigation
import com.aplication.purespace.ui.home.view.HomeScreen
import com.aplication.purespace.ui.theme.PureSpaceTheme
import com.aplication.purespace.ui.detalles.view.DetallesServicioScreen
import com.aplication.purespace.ui.pago.view.PagoDetallesScreen
import com.aplication.purespace.ui.history.view.HistoryScreen
import com.aplication.purespace.ui.servicer.view.ServicerScreen
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class MainActivity : ComponentActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var navHostController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = Firebase.auth
        setContent {
            navHostController = rememberNavController()
            PureSpaceTheme {
                Navigation(navHostController, auth)
            }
        }
    }

    override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        if (currentUser != null) {
            Log.i("Login", "User is already logged in")
        }
        auth.signOut()

    }
}