package ru.efremov.cryptoapp.presentation

import android.app.Application
import androidx.work.Configuration
import ru.efremov.cryptoapp.data.local.AppDatabase
import ru.efremov.cryptoapp.data.mapper.CoinMapper
import ru.efremov.cryptoapp.data.network.ApiFactory
import ru.efremov.cryptoapp.di.DaggerApplicationComponent
import ru.efremov.cryptoapp.workers.RefreshDataWorkerFactory
import javax.inject.Inject

class CoinApp : Application(), Configuration.Provider {

    @Inject
    lateinit var workerFactory: RefreshDataWorkerFactory

    val component by lazy {
        DaggerApplicationComponent.factory().create(this)
    }

    override fun onCreate() {
        component.inject(this)
        super.onCreate()
    }

    override fun getWorkManagerConfiguration(): Configuration {
        return Configuration.Builder()
            .setWorkerFactory(workerFactory)
            .build()
    }
}