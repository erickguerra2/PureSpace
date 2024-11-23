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
        getServices()
    }


    private fun getServices() {
        viewModelScope.launch {
            val results: List<Service> = withContext(Dispatchers.IO) {
                getAllServices()
            }
            _services.value = results

        }
    }


    private suspend fun getAllServices(): List<Service> {
        return try {
            db.collection("services")
                .get()
                .await()
                .documents
                .mapNotNull { snapShot ->
                    snapShot.toObject(Service::class.java)
                }
        } catch (e: Exception) {
            Log.e("HomeViewModel", "Error fetching services", e)
            emptyList()
        }
    }



}