package ru.efremov.cryptoapp.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import ru.efremov.cryptoapp.presentation.CoinApp
import ru.efremov.cryptoapp.presentation.CoinDetailFragment
import ru.efremov.cryptoapp.presentation.CoinPriceListActivity

@ApplicationScope
@Component(
    modules = [
        DataModule::class,
        ViewModelModule::class,
        WorkerModule::class
    ]
)
interface ApplicationComponent {

    fun inject(activity: CoinPriceListActivity)
    fun inject(fragment: CoinDetailFragment)
    fun inject(application: CoinApp)

    @Component.Factory
    interface Factory {

        fun create(
            @BindsInstance application: Application
        ): ApplicationComponent
    }
}