package com.aplication.purespace.ui.pago.viewmodel

data class PagoViewModel(
    //informacion tarjeta de credito
    val numeroTarjeta: String,
    val nombreTitular: String,
    val fechaExpiracion: String,
    val codigoSeguridad: String,
)