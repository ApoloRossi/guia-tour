package com.guiatour.data

import kotlinx.coroutines.delay

class PlacesRepositoryImpl : PlacesRepository {

    private val allPlaces = listOf(
        listOf("JaraguáXXX", "Ibirapuera", "Ibirapuera", "Ibirapuera", "Ibirapuera", "Ibirapuera", "Ibirapuera"),
        listOf("Ibirapuera", "Ibirapuera", "Ibirapuera", "Ibirapuera", "Ibirapuera", "Ibirapuera", "Ibirapuera"),
        listOf("AspicuetaY", "Aspicueta", "Ibirapuera", "Ibirapuera", "Ibirapuera")
    )

    override suspend fun fetchPlaces(): List<List<String>> {
        delay(2000)
        return allPlaces
    }
}