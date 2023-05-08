package com.ban.cryptomarket.cryptomarket.domain

import com.ban.cryptomarket.core.domain.util.CommonFlow
import com.ban.cryptomarket.cryptomarket.data.repository.CryptoMarketRepository
import com.ban.cryptomarket.data.model.CryptocurrencyItem

class ReadCryptoMarketUseCase(
    private val repository: CryptoMarketRepository
) {
    suspend fun execute(): CommonFlow<List<CryptocurrencyItem>> =
        repository.getCryptoMarket()
}