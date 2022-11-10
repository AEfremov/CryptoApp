package ru.efremov.cryptoapp.presentation

import android.app.Application
import androidx.work.Configuration
import ru.efremov.cryptoapp.di.DaggerApplicationComponent
import ru.efremov.cryptoapp.workers.CoinWorkerFactory
import javax.inject.Inject

class CoinApp : Application(), Configuration.Provider {

    @Inject
    lateinit var workerFactory: CoinWorkerFactory

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