package ru.efremov.cryptoapp.presentation

import androidx.lifecycle.ViewModel
import ru.efremov.cryptoapp.data.repository.CoinRepositoryImpl
import ru.efremov.cryptoapp.domain.GetCoinInfoListUseCase
import ru.efremov.cryptoapp.domain.GetCoinInfoUseCase
import ru.efremov.cryptoapp.domain.LoadDataUseCase
import javax.inject.Inject

class CoinViewModel @Inject constructor(
    private val getCoinInfoUseCase: GetCoinInfoUseCase,
    private val getCoinInfoListUseCase: GetCoinInfoListUseCase,
    private val loadDataUseCase: LoadDataUseCase,
) : ViewModel() {

    val coinInfoList = getCoinInfoListUseCase()

    fun getDetailInfo(fSym: String) = getCoinInfoUseCase(fSym)

    init {
        loadDataUseCase()
    }

}