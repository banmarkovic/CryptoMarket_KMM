package com.ban.cryptomarket.data.remote

import com.ban.cryptomarket.data.model.CryptocurrencyDto

interface CryptoMarketClient {
    suspend fun fetchCryptocurrencies(): List<CryptocurrencyDto>
}