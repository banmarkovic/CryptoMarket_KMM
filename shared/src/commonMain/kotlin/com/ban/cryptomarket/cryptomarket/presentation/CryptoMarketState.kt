package com.ban.cryptomarket.cryptomarket.presentation

import com.ban.cryptomarket.data.model.CryptocurrencyItem

data class CryptoMarketState(
    val cryptocurrencies: List<CryptocurrencyItem> = emptyList(),
    val isLoading: Boolean = false
)