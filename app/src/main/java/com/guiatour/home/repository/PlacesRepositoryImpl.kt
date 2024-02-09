package com.guiatour.home.repository

import com.guiatour.home.repository.local.PlacesLocalDataSource
import com.guiatour.home.repository.remote.PlacesRemoteDataSource
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PlacesRepositoryImpl @Inject constructor(
    private val remote: PlacesRemoteDataSource,
    private val local: PlacesLocalDataSource
) : PlacesRepository {

    override suspend fun fetchPlacesByCategory(category: String) =
        run {
            delay(2000)
            local.getPlaceFromLocal(category) ?: fetchRemote(category)
        }

    private suspend fun fetchRemote(category: String) =
        remote.fetchPlacesByCategory(category).map {
                    println("Request collected, save local $it")
                    if ( it.placesByCategory.isNotEmpty() ) {
                        local.savePlacesToLocal(it)
                    }
                    it
                }

}