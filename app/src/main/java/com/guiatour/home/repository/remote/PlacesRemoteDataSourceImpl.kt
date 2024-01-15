package com.guiatour.home.repository.remote

import com.guiatour.home.data.Places
import com.guiatour.home.repository.service.ParksService
import com.guiatour.home.repository.service.PartiesService
import com.guiatour.home.repository.service.PubsService
import kotlinx.coroutines.flow.flow
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