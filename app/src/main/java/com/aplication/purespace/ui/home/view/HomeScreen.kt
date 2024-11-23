package com.aplication.purespace.ui.home.view

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aplication.purespace.R
import com.aplication.purespace.ui.selectstaff.view.StaffMember
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import coil.compose.AsyncImage
import com.aplication.purespace.model.Service
import com.aplication.purespace.ui.home.viewmodel.HomeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navigateToSelectStaff: (List<StaffMember>) -> Unit, navigateToHistory: () -> Unit, viewModel: HomeViewModel = HomeViewModel()) {

    val services = viewModel.services.collectAsState()

    Log.d("HomeScreen", "Services: ${services.value}")

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
            IconButton(onClick = { navigateToHistory() }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_ajustes),
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
                    painter = painterResource(id = R.drawable.ic_busqueda),
                    contentDescription = "Search Icon"
                )
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Servicios recomendados section
        Text(
            text = "Servicios recomendados",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(modifier = Modifier.padding(start = 16.dp)
            .clickable { navigateToSelectStaff(staffList) },
            text = "Siguiente paso",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))

        // Lista de servicios recomendados

        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(services.value) {
                RecommendedServiceCard(it, {navigateToSelectStaff(staffList)})
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

@Composable
fun RecommendedServiceCard(service: Service, navigateToSelectStaff: (List<StaffMember>) -> Unit) {
    Column(
        modifier = Modifier
            .width(160.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(Color.White)
            .clickable { navigateToSelectStaff(listOf()) }
    ) {
        AsyncImage(
            model = service.imageRes.orEmpty(),
            placeholder = painterResource(R.drawable.servicio_limpieza), // Reemplaza con un recurso válido
            error = painterResource(R.drawable.servicio_planchado), // Imagen de error
            contentDescription = "services",
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = service.title.orEmpty(),
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 8.dp)
        )
        Text(
            text = service.description.orEmpty(),
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
        )
    }
}
