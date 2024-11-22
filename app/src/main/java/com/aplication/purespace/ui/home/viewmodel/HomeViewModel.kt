package com.aplication.purespace.ui.home.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aplication.purespace.model.Service
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class HomeViewModel : ViewModel() {
    private var db: FirebaseFirestore = Firebase.firestore

    private val _services = MutableStateFlow<List<Service>>(emptyList())
    val services: StateFlow<List<Service>> = _services

    init {
        _services.value = listOf(
            Service("1", "Limpieza del hogar", "Realizamos una limpieza completa del hogar", "https://www.camarounds.com/wp-content/uploads/2020/08/Servicios-de-limpieza-a-domicilio-profesional.jpg"),
            Service("2", "Servicio de planchado", "Realizamos planchado de ropa", "https://cdn1.totalcommerce.cloud/casalimpia/product-image/es/servicio-premium-de-planchado-a-domicilio-2.webp"),
            Service("3", "Limpieza de ventanas", "Realizamos limpieza de ventanas", "https://marube.es/modules/dbblog/views/img/post/84-limpieza-de-ventanas-en-verano-trucos-para-un-brillo-impecable-big.jpg")
        )
    }


    private fun getServices() {
        viewModelScope.launch {
            val result: List<Service> = withContext(Dispatchers.IO) {
                getAllServices()
            }
            _services.value = result
        }
    }

    private suspend fun getAllServices(): List<Service> {
        return try {
            val querySnapshot = db.collection("services").get().await()
            Log.d("HomeViewModel", "Fetched ${querySnapshot.documents.size} documents")
            querySnapshot.documents.mapNotNull { snapshot ->
                Log.d("HomeViewModel", "Document Data: ${snapshot.data}")
                snapshot.toObject(Service::class.java)
            }
        } catch (e: Exception) {
            Log.e("HomeViewModel", "Error fetching services", e)
            emptyList()
        }
    }



}