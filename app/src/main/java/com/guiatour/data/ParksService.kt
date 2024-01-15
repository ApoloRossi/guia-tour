package com.guiatour.data

import retrofit2.http.GET

interface ParksService {
    @GET("parks.json")
    suspend fun fetchParks(): Places
}