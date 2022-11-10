package ru.efremov.cryptoapp.presentation

import android.app.Application
import ru.efremov.cryptoapp.di.DaggerApplicationComponent

class CoinApp : Application() {

    val component by lazy {
        DaggerApplicationComponent.factory().create(this)
    }
}