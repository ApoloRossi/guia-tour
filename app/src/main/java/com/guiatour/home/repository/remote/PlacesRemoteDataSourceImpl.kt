package com.guiatour.home.repository.remote

import com.guiatour.home.data.Place
import com.guiatour.home.data.Places
import com.guiatour.home.repository.service.ParksService
import com.guiatour.home.repository.service.PartiesService
import com.guiatour.home.repository.service.PubsService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class PlacesRemoteDataSourceImpl @Inject constructor() : PlacesRemoteDataSource {

    companion object {
        private const val BASE_URL =
            "https://raw.githubusercontent.com/ApoloRossi/guia-tour/main/app/sampledata/"
    }

    private fun <T> createBuilder(clazz: Class<T>) = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(clazz)

    override suspend fun fetchPlacesByCategory(category: String) = flow {
        println("Start request remote: $category")
        val places = when (category) {
            "Parques" -> {
                kotlinx.coroutines.delay(2000L)
                createBuilder(ParksService::class.java).fetchParks()
                //Places("Parques", emptyList())
                //throw Exception("Error")
            }

            "Bares" -> {
                kotlinx.coroutines.delay(1000L)
                createBuilder(PubsService::class.java).fetchPubs()
                //Places("Bares", emptyList())
                //throw Exception("Error")
            }

            "Baladas" -> {
                kotlinx.coroutines.delay(4000L)
                createBuilder(PartiesService::class.java).fetchParties()
                //Places("Baladas", emptyList())
                //throw Exception("Error")
            } else -> {
                Places(category,
                    listOf(
                        Place(name = "Mock1", description = "Mock", image = "Mock"),
                        Place(name = "Mock2", description = "Mock", image = "Mock"),
                        Place(name = "Mock3", description = "Mock", image = "Mock"),
                        Place(name = "Mock4", description = "Mock", image = "Mock")
                    )
                )
            }
        }

        places.let {
            emit(it)
        }
    }.catch {
        throw it
    }.flowOn(Dispatchers.IO)
}