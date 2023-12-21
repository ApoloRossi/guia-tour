package com.guiatour.data

import com.google.gson.Gson
import kotlinx.coroutines.delay

class PlacesRepositoryImpl : PlacesRepository {

   private val responseParks = //"{" +
           //"\"places\": [\n" +
           "{" +
               "\"category\": \"Parques\"," +
               "\"places_by_category\": " +
                    "[{" +
                       "\"name\": \"Jaraguá\"," +
                       "\"description\": \"Jaraguá\"," +
                       "\"image\": \"Jaraguá\"" +
                    "}," + "{" +
                       "\"name\": \"Ibirapuera\"," +
                       "\"description\": \"Jaraguá\"," +
                       "\"image\": \"Jaraguá\"" +
                    "}," + "{" +
                       "\"name\": \"Vila Lobos\"," +
                       "\"description\": \"Jaraguá\"," +
                       "\"image\": \"Jaraguá\"" +
                    "}," + "{" +
                       "\"name\": \"Taquaral\"," +
                       "\"description\": \"Jaraguá\"," +
                       "\"image\": \"Jaraguá\"" +
                    "}" +
                "]" +
           "}"


    private val responsePubs = //"{" +
           //"\"places\": [\n" +
           "{" +
               "\"category\": \"Bares\"," +
               "\"places_by_category\": " +
                    "[{" +
                       "\"name\": \"Jaraguá\"," +
                       "\"description\": \"Jaraguá\"," +
                       "\"image\": \"Jaraguá\"" +
                    "}," + "{" +
                       "\"name\": \"Ibirapuera\"," +
                       "\"description\": \"Jaraguá\"," +
                       "\"image\": \"Jaraguá\"" +
                    "}," + "{" +
                       "\"name\": \"Vila Lobos\"," +
                       "\"description\": \"Jaraguá\"," +
                       "\"image\": \"Jaraguá\"" +
                    "}," + "{" +
                       "\"name\": \"Taquaral\"," +
                       "\"description\": \"Jaraguá\"," +
                       "\"image\": \"Jaraguá\"" +
                    "}" +
                "]" +
           "}"

    private val responseParties = //"{" +
           //"\"places\": [\n" +
           "{" +
               "\"category\": \"Baladas\"," +
               "\"places_by_category\": " +
                    "[{" +
                       "\"name\": \"Jaraguá\"," +
                       "\"description\": \"Jaraguá\"," +
                       "\"image\": \"Jaraguá\"" +
                    "}," + "{" +
                       "\"name\": \"Ibirapuera\"," +
                       "\"description\": \"Jaraguá\"," +
                       "\"image\": \"Jaraguá\"" +
                    "}," + "{" +
                       "\"name\": \"Vila Lobos\"," +
                       "\"description\": \"Jaraguá\"," +
                       "\"image\": \"Jaraguá\"" +
                    "}," + "{" +
                       "\"name\": \"Taquaral\"," +
                       "\"description\": \"Jaraguá\"," +
                       "\"image\": \"Jaraguá\"" +
                    "}" +
                "]" +
           "}"




    override suspend fun fetchPlacesByCategory(category: String): Places {
        val response = when(category) {
            "Parques" -> {
                delay(2000)
                responseParks
            }
            "Bares" -> {
                delay(5000)
                responsePubs
            }
            "Baladas" -> {
                delay(8000)
                responseParties
            }
            else -> ""
        }

        val placesResponse = Gson().fromJson(response, Places::class.java)
        return placesResponse
    }
}