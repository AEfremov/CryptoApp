package ru.efremov.cryptoapp.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import ru.efremov.cryptoapp.presentation.CoinDetailFragment
import ru.efremov.cryptoapp.presentation.CoinPriceListActivity

@Component(
    modules = [
        DataModule::class,
        ViewModelModule::class
    ]
)
interface ApplicationComponent {

    fun inject(activity: CoinPriceListActivity)
    fun inject(fragment: CoinDetailFragment)

    @Component.Factory
    interface Factory {

        fun create(
            @BindsInstance application: Application
        ): ApplicationComponent
    }
}