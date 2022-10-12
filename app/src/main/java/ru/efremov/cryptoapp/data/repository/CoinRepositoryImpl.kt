package ru.efremov.cryptoapp.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import kotlinx.coroutines.delay
import ru.efremov.cryptoapp.data.local.AppDatabase
import ru.efremov.cryptoapp.data.local.CoinInfoDao
import ru.efremov.cryptoapp.data.mapper.CoinMapper
import ru.efremov.cryptoapp.data.network.ApiFactory
import ru.efremov.cryptoapp.data.network.ApiService
import ru.efremov.cryptoapp.domain.CoinInfo
import ru.efremov.cryptoapp.domain.CoinRepository

class CoinRepositoryImpl(
    application: Application
): CoinRepository {

    private val coinInfoDao: CoinInfoDao = AppDatabase.getInstance(application).coinPriceInfoDao()
    private val service = ApiFactory.apiService

    private val mapper = CoinMapper()

    override fun getCoinInfoList(): LiveData<List<CoinInfo>> {
        return Transformations.map(coinInfoDao.getPriceList()) {
            it.map { dbModel ->
                mapper.mapDbModelToEntity(dbModel)
            }
        }
    }

    override fun getCoinInfo(fromSymbol: String): LiveData<CoinInfo> {
        return Transformations.map(coinInfoDao.getPriceInfoAboutCoin(fromSymbol)) {
            mapper.mapDbModelToEntity(it)
        }
    }

    override suspend fun loadData() {
        while (true) {
            try {
                val topCoins = service.getTopCoinsInfo(limit = 50)
                val fSyms = mapper.mapNamesListToString(topCoins)
                val jsonContainer = service.getFullPriceList(fSyms = fSyms)
                val coinInfoDtoList = mapper.mapJsonContainerToListCoinInfo(jsonContainer)
                val dbModelList = coinInfoDtoList.map { mapper.mapDtoToDbModel(it) }
                coinInfoDao.insertPriceList(dbModelList)
            } catch (error: Throwable) {
            }
            delay(10000)
        }
    }
}