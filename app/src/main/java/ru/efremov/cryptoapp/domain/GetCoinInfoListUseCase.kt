package ru.efremov.cryptoapp.domain

import androidx.lifecycle.LiveData
import javax.inject.Inject

class GetCoinInfoListUseCase @Inject constructor(
    private val repository: CoinRepository
) {

    operator fun invoke(): LiveData<List<CoinInfo>> = repository.getCoinInfoList()
}