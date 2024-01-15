package com.guiatour.data

import retrofit2.http.GET

interface PubsService {
    @GET("pubs.json")
    suspend fun fetchPubs(): Places
}