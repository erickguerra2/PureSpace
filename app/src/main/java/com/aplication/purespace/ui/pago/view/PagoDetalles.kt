package com.aplication.purespace.ui.pago.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun PagoDetallesScreen(navigateToHome: () -> Unit) {
    var cardNumber by remember { mutableStateOf("") }
    var expirationDate by remember { mutableStateOf("") }
    var cvv by remember { mutableStateOf("") }
    var cardholderName by remember { mutableStateOf("") }
    var postalCode by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        // Título
        Text(
            text = "Detalles de pago",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Campo de Número de Tarjeta
        BasicTextField(
            value = cardNumber,
            onValueChange = { cardNumber = it },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .background(Color.LightGray, shape = MaterialTheme.shapes.medium)
                .padding(16.dp),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            decorationBox = { innerTextField ->
                if (cardNumber.isEmpty()) {
                    Text(text = "0000 0000 0000 0000", color = Color.Gray)
                }
                innerTextField()
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Vencimiento y CVV
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            BasicTextField(
                value = expirationDate,
                onValueChange = { expirationDate = it },
                modifier = Modifier
                    .weight(1f)
                    .height(56.dp)
                    .background(Color.LightGray, shape = MaterialTheme.shapes.medium)
                    .padding(16.dp),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                decorationBox = { innerTextField ->
                    if (expirationDate.isEmpty()) {
                        Text(text = "MM/AA", color = Color.Gray)
                    }
                    innerTextField()
                }
            )
            BasicTextField(
                value = cvv,
                onValueChange = { cvv = it },
                modifier = Modifier
                    .weight(1f)
                    .height(56.dp)
                    .background(Color.LightGray, shape = MaterialTheme.shapes.medium)
                    .padding(16.dp),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                decorationBox = { innerTextField ->
                    if (cvv.isEmpty()) {
                        Text(text = "123", color = Color.Gray)
                    }
                    innerTextField()
                }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Nombre en la tarjeta y Código postal
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            BasicTextField(
                value = cardholderName,
                onValueChange = { cardholderName = it },
                modifier = Modifier
                    .weight(1f)
                    .height(56.dp)
                    .background(Color.LightGray, shape = MaterialTheme.shapes.medium)
                    .padding(16.dp),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text),
                decorationBox = { innerTextField ->
                    if (cardholderName.isEmpty()) {
                        Text(text = "Nombre en la tarjeta", color = Color.Gray)
                    }
                    innerTextField()
                }
            )
            Button(
                onClick = { /* Acción de agregar */ },
                modifier = Modifier
                    .height(56.dp)
                    .weight(0.5f)
            ) {
                Text(text = "Agregar")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            BasicTextField(
                value = postalCode,
                onValueChange = { postalCode = it },
                modifier = Modifier
                    .weight(1f)
                    .height(56.dp)
                    .background(Color.LightGray, shape = MaterialTheme.shapes.medium)
                    .padding(16.dp),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                decorationBox = { innerTextField ->
                    if (postalCode.isEmpty()) {
                        Text(text = "Código postal", color = Color.Gray)
                    }
                    innerTextField()
                }
            )
            Button(
                onClick = { /* Acción de agregar */ },
                modifier = Modifier
                    .height(56.dp)
                    .weight(0.5f)
            ) {
                Text(text = "Agregar")
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Costo del servicio
        Text(text = "Costo del servicio", fontWeight = FontWeight.Bold, fontSize = 18.sp)

        Spacer(modifier = Modifier.height(8.dp))

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(text = "Servicio de limpieza", fontSize = 16.sp)
            Text(text = "$200", fontSize = 16.sp, fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(text = "Monto total", fontSize = 16.sp)
            Text(text = "$200", fontSize = 16.sp, fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Botón Confirmar y reservar
        Button(
            onClick = { navigateToHome() },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF007BFF))
        ) {
            Text(text = "Confirmar y reservar", fontSize = 18.sp, color = Color.White)
        }
    }
}