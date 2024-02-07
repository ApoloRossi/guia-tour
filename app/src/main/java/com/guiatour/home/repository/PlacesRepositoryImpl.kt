package com.guiatour.home.repository

import com.guiatour.home.repository.local.PlacesLocalDataSource
import com.guiatour.home.repository.remote.PlacesRemoteDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PlacesRepositoryImpl @Inject constructor(
    private val remote: PlacesRemoteDataSource,
    private val local: PlacesLocalDataSource
) : PlacesRepository {

    override suspend fun fetchPlacesByCategory(category: String, coroutineScope: CoroutineScope) =
        run {
            local.getPlaceFromLocal(category, coroutineScope) ?: fetchRemote(category, coroutineScope)
        }

    private suspend fun fetchRemote(category: String, coroutineScope: CoroutineScope) =
        remote.fetchPlacesByCategory(category).map {
                    println("Request collected, save local $it")
                    local.savePlacesToLocal(it)
                    it
                }

}