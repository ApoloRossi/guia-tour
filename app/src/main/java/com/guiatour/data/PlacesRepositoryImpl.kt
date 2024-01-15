package com.guiatour.data

import kotlinx.coroutines.flow.flow
import retrofit2.Retrofit
import javax.inject.Inject
import retrofit2.converter.gson.GsonConverterFactory

class PlacesRepositoryImpl @Inject constructor() : PlacesRepository {

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

        val places = when (category) {
            "Parques" -> {
                createBuilder(ParksService::class.java).fetchParks() as Places
            }

            "Bares" -> {
                createBuilder(PubsService::class.java).fetchPubs() as Places
            }

            "Baladas" -> {
                createBuilder(PartiesService::class.java).fetchParties() as Places
            } else -> {
                null
            }
        }

        places?.let {
            emit(it)
        }
    }
}