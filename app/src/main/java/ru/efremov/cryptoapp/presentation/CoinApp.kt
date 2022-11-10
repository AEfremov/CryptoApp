package ru.efremov.cryptoapp.presentation

import android.app.Application
import androidx.work.Configuration
import ru.efremov.cryptoapp.data.local.AppDatabase
import ru.efremov.cryptoapp.data.mapper.CoinMapper
import ru.efremov.cryptoapp.data.network.ApiFactory
import ru.efremov.cryptoapp.di.DaggerApplicationComponent
import ru.efremov.cryptoapp.workers.RefreshDataWorkerFactory

class CoinApp : Application(), Configuration.Provider {

    val component by lazy {
        DaggerApplicationComponent.factory().create(this)
    }

    override fun getWorkManagerConfiguration(): Configuration {
        return Configuration.Builder()
            .setWorkerFactory(
                RefreshDataWorkerFactory(
                    AppDatabase.getInstance(this).coinPriceInfoDao(),
                    ApiFactory.apiService,
                    CoinMapper()
                )
            )
            .build()
    }
}