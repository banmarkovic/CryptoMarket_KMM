package com.ban.cryptomarket.data.local

import com.ban.cryptomarket.core.domain.util.CommonFlow
import com.ban.cryptomarket.data.model.CryptocurrencyItem

interface CryptoMarketDataSource {
    fun getMarket(): CommonFlow<List<CryptocurrencyItem>>
    suspend fun insertCryptocurrencyItem(item: CryptocurrencyItem)
    suspend fun deleteAll()
}