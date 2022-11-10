package ru.efremov.cryptoapp.di

import android.app.Application
import dagger.Binds
import dagger.Module
import dagger.Provides
import ru.efremov.cryptoapp.data.local.AppDatabase
import ru.efremov.cryptoapp.data.local.CoinInfoDao
import ru.efremov.cryptoapp.data.repository.CoinRepositoryImpl
import ru.efremov.cryptoapp.domain.CoinRepository

@Module
interface DataModule {

    @Binds
    fun bindCoinRepository(impl: CoinRepositoryImpl): CoinRepository

    companion object {

        @Provides
        fun provideCoinInfoDao(
            application: Application
        ): CoinInfoDao {
            return AppDatabase.getInstance(application).coinPriceInfoDao()
        }
    }
}