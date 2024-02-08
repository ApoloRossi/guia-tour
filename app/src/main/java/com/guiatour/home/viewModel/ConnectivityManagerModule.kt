package com.guiatour.home.viewModel

import android.content.Context
import android.net.ConnectivityManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class ConnectivityManagerModule {

    companion object {
        @Provides
        fun bindConnectivityManager(
            @ApplicationContext context: Context
        ) = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }
}