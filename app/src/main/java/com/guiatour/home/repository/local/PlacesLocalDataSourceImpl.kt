package com.guiatour.home.repository.local

import com.guiatour.home.data.Places
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PlacesLocalDataSourceImpl @Inject constructor() : PlacesLocalDataSource {
    override suspend fun getPlaceFromLocal(category: String): Flow<Places>? {
        return null
    }
}