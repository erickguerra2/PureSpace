package com.aplication.purespace.ui.selectstaff.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aplication.purespace.R

// Clase de datos para representar a un miembro del personal
data class StaffMember(
    val id: Int,
    val name: String,
    val rating: Float,
    val servicesCompleted: Int,
    val imageRes: Int // Recurso de imagen (drawable)
)

@Composable
fun SelectStaffScreen(navigateToServicer: () -> Unit) {
    // Lista de ejemplo de Staff dentro de la función
    val staffList = listOf(
        StaffMember(1, "Carlos Lopez", 4.8f, 125, R.drawable.staff_image_1),
        StaffMember(2, "Ana Martinez", 4.6f, 100, R.drawable.staff_image_2),
        StaffMember(3, "Maria Fernandez", 4.9f, 140, R.drawable.staff_image_3)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Título
        Text(
            text = "Selecciona un profesional",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Lista de personal usando LazyColumn
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(staffList) { staffMember ->
                StaffCard(staffMember, {navigateToServicer()})
            }
        }
    }
}

@Composable
fun StaffCard(staffMember: StaffMember, navigateToServicer: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { navigateToServicer() },
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Mostrar la foto del profesional
            Image(
                painter = painterResource(id = staffMember.imageRes),
                contentDescription = null,
                modifier = Modifier
                    .size(64.dp)
                    .clip(CircleShape), // Hacer la imagen redonda
                contentScale = ContentScale.Crop // Asegura que la imagen se recorte correctamente
            )

            Spacer(modifier = Modifier.width(16.dp))

            // Mostrar la información del profesional
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = staffMember.name,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "${staffMember.servicesCompleted} servicios completados",
                    style = MaterialTheme.typography.bodySmall
                )
            }

            // Mostrar la calificación del profesional
            Text(
                text = "${staffMember.rating} ★",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}

