package ru.efremov.cryptoapp.di

import androidx.work.ListenableWorker
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ru.efremov.cryptoapp.workers.ChildWorkerFactory
import ru.efremov.cryptoapp.workers.RefreshDataWorker

@Module
interface WorkerModule {

    @Binds
    @IntoMap
    @WorkerKey(RefreshDataWorker::class)
    fun bindRefreshDataWorkerFactory(refreshDataWorker: RefreshDataWorker.Factory): ChildWorkerFactory
}