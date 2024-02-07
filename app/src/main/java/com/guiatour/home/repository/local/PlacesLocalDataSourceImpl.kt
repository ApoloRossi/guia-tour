package com.guiatour.home.repository.local

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.google.gson.Gson
import com.guiatour.home.data.Places
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class PlacesLocalDataSourceImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : PlacesLocalDataSource {

    companion object {
        const val PLACES_DATASTORE_NAME = "places"
    }

    private val Context.dataStore by preferencesDataStore(name = PLACES_DATASTORE_NAME)

    override suspend fun getPlaceFromLocal(
        category: String
    ): Flow<Places>? {
        context.dataStore.data.flowOn(Dispatchers.IO).first().let {
            it[stringPreferencesKey(category)]?.let { placesJson ->
                return flow {
                    println("Start request from local: $category")
                    emit(Gson().fromJson(placesJson, Places::class.java))
                }
            }
        }
        return null
    }

    override suspend fun savePlacesToLocal(places: Places) {
        context.dataStore.edit { settings ->
            settings[stringPreferencesKey(places.category)] = placesToJson(places)
        }
    }

    private fun placesToJson(places: Places) = Gson().toJson(places)
}