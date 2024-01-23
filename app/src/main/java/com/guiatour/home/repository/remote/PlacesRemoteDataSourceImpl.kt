package com.guiatour.home.repository.remote

import com.guiatour.home.repository.service.ParksService
import com.guiatour.home.repository.service.PartiesService
import com.guiatour.home.repository.service.PubsService
import kotlinx.coroutines.flow.catch
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
                kotlinx.coroutines.delay(2000L)
                createBuilder(ParksService::class.java).fetchParks()
            }

            "Bares" -> {
                kotlinx.coroutines.delay(1000L)
                createBuilder(PubsService::class.java).fetchPubs()
            }

            "Baladas" -> {
                kotlinx.coroutines.delay(4000L)
                createBuilder(PartiesService::class.java).fetchParties()
            } else -> {
                null
            }
        }

        places?.let {
            emit(it)
        }
    }.catch {
        throw it
    }
}