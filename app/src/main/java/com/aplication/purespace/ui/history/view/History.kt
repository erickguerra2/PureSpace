package com.aplication.purespace.ui.history.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aplication.purespace.R


@Composable
fun HistoryScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Título principal
        Text(
            text = "Your payments",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Sección de pagos del 2022
        Text(
            text = "2022",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(vertical = 8.dp)
        )
        PaymentItem(
            imageRes = R.drawable.limpieza, // Imagen renombrada a minúsculas
            name = "Sophia",
            date = "Jan 7, 2022",
            cardInfo = "VISA ....9485",
            amount = "$80.00"
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Sección de pagos del 2021
        Text(
            text = "2021",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(vertical = 8.dp)
        )
        PaymentItem(
            imageRes = R.drawable.limpieza2, // Imagen renombrada a minúsculas
            name = "Sophia",
            date = "Dec 12, 2021",
            cardInfo = "VISA ....9485",
            amount = "$80.00"
        )
        Spacer(modifier = Modifier.height(16.dp))
        PaymentItem(
            imageRes = R.drawable.limpieza3_1, // Imagen renombrada a minúsculas
            name = "Sophia",
            date = "Jul 11, 2021",
            cardInfo = "VISA ....9485",
            amount = "$80.00"
        )
    }

    // Barra de navegación inferior
    BottomNavigationBar()
}

@Composable
fun PaymentItem(imageRes: Int, name: String, date: String, cardInfo: String, amount: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White, shape = RoundedCornerShape(8.dp))
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = null,
                modifier = Modifier
                    .size(60.dp)
                    .background(Color.LightGray, shape = CircleShape),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text(text = "Paid • $name", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                Text(text = date, fontSize = 14.sp, color = Color.Gray)
                Text(text = cardInfo, fontSize = 14.sp, color = Color.Gray)
            }
        }
        Text(text = amount, fontSize = 16.sp, fontWeight = FontWeight.Bold)
    }
}

@Composable
fun BottomNavigationBar() {
    NavigationBar(
        modifier = Modifier.fillMaxWidth(),
        containerColor = Color.White  // Cambiamos de backgroundColor a containerColor
    ) {
        NavigationBarItem(
            selected = false,
            onClick = { /* Acción al presionar Home */ },
            icon = { Text("Home") }
        )
        NavigationBarItem(
            selected = false,
            onClick = { /* Acción al presionar Bookings */ },
            icon = { Text("Bookings") }
        )
        NavigationBarItem(
            selected = true,  // Estamos en Payments
            onClick = { /* Acción al presionar Payments */ },
            icon = { Text("Payments") }
        )
        NavigationBarItem(
            selected = false,
            onClick = { /* Acción al presionar Profile */ },
            icon = { Text("Profile") }
        )
    }
}