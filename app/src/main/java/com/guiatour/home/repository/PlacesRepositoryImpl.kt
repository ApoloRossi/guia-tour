package com.guiatour.home.repository

import com.guiatour.home.repository.local.PlacesLocalDataSource
import com.guiatour.home.repository.remote.PlacesRemoteDataSource
import javax.inject.Inject

class PlacesRepositoryImpl @Inject constructor(
    private val remote: PlacesRemoteDataSource,
    private val local: PlacesLocalDataSource
) : PlacesRepository {

    override suspend fun fetchPlacesByCategory(category: String) = run {
        local.getPlaceFromLocal(category) ?: fetchRemote(category)
    }

    private suspend fun fetchRemote(category: String) =
        remote.fetchPlacesByCategory(category).apply {
            this.collect {
                local.savePlacesToLocal(it)
            }
        }
}