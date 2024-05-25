package com.guiatour.home.repository.service

import com.guiatour.home.data.Categories
import com.guiatour.home.data.Places
import retrofit2.http.GET

interface MostViewedService {
    @GET("mostSeenCategories.json")
    suspend fun fetchMostSeenCategories(): Categories
}