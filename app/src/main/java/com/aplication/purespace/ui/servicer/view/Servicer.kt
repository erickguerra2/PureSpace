package com.aplication.purespace.ui.servicer.view

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
fun ServicerScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Título principal
        Text(
            text = "Incoming Requests",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Lista de solicitudes
        RequestItem(
            imageRes = R.drawable.staff_image_1, // Imagen subida
            name = "Alisha",
            description = "2 bedroom, 2 bathroom"
        )
        RequestItem(
            imageRes = R.drawable.staff_image_2, // Imagen subida
            name = "Kendall",
            description = "1 bedroom, 1 bathroom"
        )
        RequestItem(
            imageRes = R.drawable.staff_image_3, // Imagen subida
            name = "Flora",
            description = "3 bedroom, 2 bathroom"
        )

        // Botón "Accept All"
        Spacer(modifier = Modifier.weight(1f))
        Button(
            onClick = { /* Acción al presionar "Accept All" */ },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF007BFF))
        ) {
            Text(text = "Accept All", fontSize = 18.sp, color = Color.White)
        }
    }
}

@Composable
fun RequestItem(imageRes: Int, name: String, description: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = null,
                modifier = Modifier
                    .size(60.dp)
                    .background(Color.LightGray, shape = CircleShape),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(text = name, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                Text(text = description, fontSize = 14.sp, color = Color.Gray)
            }
        }
        Spacer(modifier = Modifier.height(8.dp))

        // Botones "Accept" y "Decline"
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = { /* Acción al aceptar */ },
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF007BFF)),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(text = "Accept")
            }
            Spacer(modifier = Modifier.width(16.dp))
            Button(
                onClick = { /* Acción al declinar */ },
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(containerColor = Color.LightGray),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(text = "Decline", fontWeight = FontWeight.Bold)
            }
        }
    }
}