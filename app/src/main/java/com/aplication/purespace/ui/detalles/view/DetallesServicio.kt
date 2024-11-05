package com.aplication.purespace.ui.detalles.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.util.*

@Composable
fun DetallesServicioScreen(navigateToPay: () -> Unit) {
    var selectedDate by remember { mutableStateOf("5 Ene 2023") }
    var selectedTime by remember { mutableStateOf("11:00 AM") }
    var additionalDetails by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "¿Cuándo nos necesitas?",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Fecha y Hora",
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Calendario
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
                .background(Color.LightGray),
            contentAlignment = Alignment.Center
        ) {
            Text(text = selectedDate, fontSize = 24.sp, fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Selección de hora
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .background(Color.Gray, shape = MaterialTheme.shapes.medium),
            contentAlignment = Alignment.Center
        ) {
            Text(text = selectedTime, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.Black)
        }

        Spacer(modifier = Modifier.height(16.dp))


        BasicTextField(
            value = additionalDetails,
            onValueChange = { additionalDetails = it },
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(Color.LightGray, shape = MaterialTheme.shapes.medium)
                .padding(8.dp),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text),
            decorationBox = { innerTextField ->
                if (additionalDetails.isEmpty()) {
                    Text(
                        text = "Agrega detalles adicionales",
                        color = Color.Gray,
                        modifier = Modifier.padding(8.dp)
                    )
                }
                innerTextField()
            }
        )

        Spacer(modifier = Modifier.height(16.dp))


        Button(
            onClick = { navigateToPay() },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF007BFF)),
            shape = MaterialTheme.shapes.medium
        ) {
            Text(text = "Continuar", fontSize = 18.sp, color = Color.White)
        }
    }
}