package com.aplication.purespace.ui.home.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aplication.purespace.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navigateToSelectStaff: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Header: Home Title and Settings Icon
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Home",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )
            IconButton(onClick = { /* Acción para configuración */ }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_ajustes), // Asegúrate de tener un ícono de settings
                    contentDescription = "Settings"
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Search Field
        TextField(
            value = "",
            onValueChange = { /* Acción de búsqueda */
            },
            placeholder = { Text("Search for a service") },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(Color(0xFFEFF1F5)),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color(0xFFEFF1F5),
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_busqueda), // Asegúrate de tener un ícono de búsqueda
                    contentDescription = "Search Icon"
                )
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Filter Buttons
        val filters = listOf("Limpieza de casa", "Limpieza de alfombras", "Limpieza de ventanas", "Limpieza de mudanza", "Limpieza post-construcción")
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement =  Arrangement.spacedBy(8.dp)
        ) {
            items(filters) { filter ->
                FilterButton(filter)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Servicios recomendados section
        Text(
            text = "Servicios recomendados",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Lista de servicios recomendados
        val recommendedServices = listOf(
            ServiceItem("Limpieza de casa", "2 limpiadores, 3 horas", R.drawable.servicio_limpieza),
            ServiceItem("Limpieza de ventanas", "1 limpiador, 2 horas", R.drawable.servicio_ventana),
            ServiceItem("Servicio de planchado", "1 personal, 1 hora", R.drawable.servicio_planchado),
        )
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(recommendedServices) { service ->
                RecommendedServiceCard(service)
            }
        }
    }
}

@Composable
fun FilterButton(text: String) {
    Button(
        onClick = { /* Acción de filtro */ },
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .background(Color(0xFFEFF1F5)),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFFEFF1F5),
            contentColor = Color.Black
        ),
        elevation = ButtonDefaults.buttonElevation(0.dp)
    ) {
        Text(text)
    }
}

data class ServiceItem(val title: String, val description: String, val imageRes: Int)

@Composable
fun RecommendedServiceCard(service: ServiceItem) {
    Column(
        modifier = Modifier
            .width(160.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(Color.White)
    ) {
        Image(
            painter = painterResource(id = service.imageRes),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
                .clip(RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp)),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = service.title,
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 8.dp)
        )
        Text(
            text = service.description,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
        )
    }
}
