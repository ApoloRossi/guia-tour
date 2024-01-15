package com.guiatour.home.repository.service

import com.guiatour.home.data.Places
import retrofit2.http.GET

interface PartiesService {
    @GET("parties.json")
    suspend fun fetchParties(): Places
}