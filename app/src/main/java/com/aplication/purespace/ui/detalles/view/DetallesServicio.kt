package com.aplication.purespace.ui.detalles.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import java.time.LocalDate
import java.time.YearMonth

@Composable
fun DetallesServicioScreen(navigateToPay: () -> Unit) {
    var selectedDate by remember { mutableStateOf<LocalDate?>(null) }
    var additionalDetails by remember { mutableStateOf("") }
    val currentMonth = remember { mutableStateOf(YearMonth.now()) }
    val daysInMonth = generateDaysInMonth(currentMonth.value)

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
            text = "Selecciona una fecha:",
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Calendario
        WorkoutCalendar(
            daysInMonth = daysInMonth,
            selectedDay = selectedDate,
            currentMonth = currentMonth.value,
            onDayClick = { day -> selectedDate = day },
            onNextMonth = { currentMonth.value = currentMonth.value.plusMonths(1) },
            onPreviousMonth = { currentMonth.value = currentMonth.value.minusMonths(1) }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Detalles adicionales
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

        // Botón para continuar
        Button(
            onClick = {
                // Navega y pasa la fecha seleccionada
                navigateToPay()
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF007BFF)),
            shape = MaterialTheme.shapes.medium
        ) {
            Text(text = "Continuar", fontSize = 18.sp, color = Color.White)
        }
    }
}

@Composable
fun WorkoutCalendar(
    daysInMonth: List<LocalDate>,
    selectedDay: LocalDate?,
    currentMonth: YearMonth,
    onDayClick: (LocalDate) -> Unit,
    onNextMonth: () -> Unit,
    onPreviousMonth: () -> Unit
) {
    val daysOfWeek = listOf("D", "L", "M", "M", "J", "V", "S")

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "<",
                modifier = Modifier
                    .padding(8.dp)
                    .clickable { onPreviousMonth() },
                fontSize = 20.sp,
                color = Color.Gray
            )

            Text(
                text = "${currentMonth.month.name.lowercase().capitalize()} ${currentMonth.year}",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = Color.Black
            )

            Text(
                text = ">",
                modifier = Modifier
                    .padding(8.dp)
                    .clickable { onNextMonth() },
                fontSize = 20.sp,
                color = Color.Gray
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            daysOfWeek.forEach { day ->
                Text(text = day, fontSize = 14.sp, color = Color.Gray)
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        daysInMonth.chunked(7).forEach { week ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                week.forEach { day ->
                    Text(
                        text = if (day.month == currentMonth.month) day.dayOfMonth.toString() else "",
                        fontSize = 16.sp,
                        color = if (day == selectedDay) Color.Blue else Color.Black,
                        modifier = Modifier
                            .padding(8.dp)
                            .clickable { onDayClick(day) }
                    )
                }
            }
        }
    }
}

fun generateDaysInMonth(month: YearMonth): List<LocalDate> {
    val firstDayOfMonth = month.atDay(1)
    val lastDayOfMonth = month.atEndOfMonth()
    val days = mutableListOf<LocalDate>()

    // Añadir días anteriores del mes anterior para completar la primera semana
    val firstDayOfWeek = firstDayOfMonth.dayOfWeek.value % 7
    val previousMonth = month.minusMonths(1)
    val lastDayOfPreviousMonth = previousMonth.atEndOfMonth()
    for (i in firstDayOfWeek downTo 1) {
        days.add(lastDayOfPreviousMonth.minusDays((i - 1).toLong()))
    }

    // Añadir días del mes actual
    for (day in 1..lastDayOfMonth.dayOfMonth) {
        days.add(month.atDay(day))
    }

    // Añadir días del próximo mes para completar la última semana
    val remainingDays = 7 - days.size % 7
    if (remainingDays < 7) {
        val nextMonth = month.plusMonths(1)
        for (i in 1..remainingDays) {
            days.add(nextMonth.atDay(i))
        }
    }

    return days
}