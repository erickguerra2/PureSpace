package com.aplication.purespace

import LoginScreen
import android.os.Bundle
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
import com.aplication.purespace.navigation.Navigation
import com.aplication.purespace.ui.home.view.HomeScreen
import com.aplication.purespace.ui.theme.PureSpaceTheme
import com.aplication.purespace.ui.detalles.view.DetallesServicioScreen
import com.aplication.purespace.ui.pago.view.PagoDetallesScreen
import com.aplication.purespace.ui.history.view.HistoryScreen
import com.aplication.purespace.ui.servicer.view.ServicerScreen



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PureSpaceTheme {
                Navigation()
            }
        }
    }
}