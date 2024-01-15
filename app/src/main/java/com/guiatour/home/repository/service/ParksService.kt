package com.guiatour.home.repository.service

import com.guiatour.home.data.Places
import retrofit2.http.GET

interface ParksService {
    @GET("parks.json")
    suspend fun fetchParks(): Places
}