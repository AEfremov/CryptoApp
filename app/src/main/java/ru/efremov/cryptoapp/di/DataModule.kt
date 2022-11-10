package ru.efremov.cryptoapp.di

import android.app.Application
import dagger.Binds
import dagger.Module
import dagger.Provides
import ru.efremov.cryptoapp.data.local.AppDatabase
import ru.efremov.cryptoapp.data.local.CoinInfoDao
import ru.efremov.cryptoapp.data.network.ApiFactory
import ru.efremov.cryptoapp.data.network.ApiService
import ru.efremov.cryptoapp.data.repository.CoinRepositoryImpl
import ru.efremov.cryptoapp.domain.CoinRepository

@Module
interface DataModule {

    @Binds
    @ApplicationScope
    fun bindCoinRepository(impl: CoinRepositoryImpl): CoinRepository

    companion object {

        @Provides
        @ApplicationScope
        fun provideCoinInfoDao(
            application: Application
        ): CoinInfoDao {
            return AppDatabase.getInstance(application).coinPriceInfoDao()
        }

        @Provides
        @ApplicationScope
        fun provideApiService(): ApiService {
            return ApiFactory.apiService
        }
    }
}