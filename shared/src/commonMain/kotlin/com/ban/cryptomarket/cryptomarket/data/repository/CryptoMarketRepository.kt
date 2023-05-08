package com.ban.cryptomarket.cryptomarket.data.repository

import com.ban.cryptomarket.core.domain.util.CommonFlow
import com.ban.cryptomarket.data.model.CryptocurrencyItem

interface CryptoMarketRepository {
    suspend fun getCryptoMarket(): CommonFlow<List<CryptocurrencyItem>>
}