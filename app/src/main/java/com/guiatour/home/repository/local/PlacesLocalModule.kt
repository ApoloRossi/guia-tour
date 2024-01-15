package com.guiatour.home.repository.local

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class PlacesLocalModule {
    @Binds
    abstract fun bindPlacesLocalDataSource(
        placesLocalDataSourceImpl: PlacesLocalDataSourceImpl
    ) : PlacesLocalDataSource
}