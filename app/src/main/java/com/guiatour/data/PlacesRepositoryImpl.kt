package com.guiatour.data

import com.google.gson.Gson
import kotlinx.coroutines.delay

class PlacesRepositoryImpl : PlacesRepository {

   private val response = "{" +
           "\"places\": [\n" +
           "{" +
               "\"category\": \"Parques\"," +
               "\"places_by_category\": [{" +
               "\"name\": \"Jaraguá\"," +
               "\"description\": \"Jaraguá\"," +
               "\"image\": \"Jaraguá\"}]" +
           "}," +
           "{" +
               "\"category\": \"Bares\"," +
               "\"places_by_category\": [{" +
               "\"name\": \"Jaraguá\"," +
               "\"description\": \"Jaraguá\"," +
               "\"image\": \"Jaraguá\"}]" +
           "}" +
           "]" +
           "}"

    override suspend fun fetchPlacesByCategory(category: String): PlacesResponse {
        delay(2000)
        val placesResponse = Gson().fromJson(response, PlacesResponse::class.java)
        return placesResponse
    }
}