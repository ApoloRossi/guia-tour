package com.guiatour.home.repository.remote

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class PlacesRemoteModule {
    @Binds
    abstract fun bindPlacesRemoteDataSource(
        placesRemoteDataSourceImpl: PlacesRemoteDataSourceImpl
    ) : PlacesRemoteDataSource
}