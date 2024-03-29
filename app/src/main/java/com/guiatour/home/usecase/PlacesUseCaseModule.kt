package com.guiatour.home.usecase

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class PlacesUseCaseModule {
    @Binds
    abstract fun bindPlacesUseCase(
        placesUseCaseImpl: PlacesUseCaseImpl
    ) : PlacesUseCase
}