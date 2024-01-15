package com.guiatour.home.repository

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class PlacesRepositoryModule {
    @Binds
    abstract fun bindPlacesRepository(
        placesRepositoryImpl: PlacesRepositoryImpl
    ) : PlacesRepository
}