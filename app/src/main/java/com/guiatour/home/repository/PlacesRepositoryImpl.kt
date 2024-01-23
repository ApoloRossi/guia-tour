package com.guiatour.home.repository

import com.guiatour.home.repository.local.PlacesLocalDataSource
import com.guiatour.home.repository.remote.PlacesRemoteDataSource
import kotlinx.coroutines.flow.catch
import javax.inject.Inject

class PlacesRepositoryImpl @Inject constructor(
    private val remote: PlacesRemoteDataSource,
    private val local: PlacesLocalDataSource
) : PlacesRepository {

    override suspend fun fetchPlacesByCategory(category: String) = run {
        local.getPlaceFromLocal(category) ?: fetchRemote(category).catch {
            throw it
        }
    }

    private suspend fun fetchRemote(category: String) =
        remote.fetchPlacesByCategory(category).apply {
            catch {}.collect {
                local.savePlacesToLocal(it)
            }
        }
}