package com.guiatour.data

import retrofit2.http.GET

interface PartiesService {
    @GET("parties.json")
    suspend fun fetchParties(): Places
}