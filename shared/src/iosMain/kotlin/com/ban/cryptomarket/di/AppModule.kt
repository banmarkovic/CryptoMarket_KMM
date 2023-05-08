package com.ban.cryptomarket.di

import com.ban.cryptomarket.cryptomarket.data.repository.CryptoMarketRepository
import com.ban.cryptomarket.cryptomarket.domain.ReadCryptoMarketUseCase
import com.ban.cryptomarket.data.local.CryptoMarketDataSource
import com.ban.cryptomarket.data.local.SqlDelightCryptoMarketDataSource
import com.ban.cryptomarket.data.remote.CryptoMarketClient
import com.ban.cryptomarket.data.remote.KtorCryptoMarketClient
import com.ban.cryptomarket.data.repository.CryptoMarketRepositoryImpl
import com.ban.cryptomarket.data.util.DatabaseDriverFactory
import com.ban.cryptomarket.data.util.HttpClientFactory
import com.ban.cryptomarket.database.MarketDatabase

class AppModule {

    private val cryptoMarketDataSource: CryptoMarketDataSource by lazy {
        SqlDelightCryptoMarketDataSource(MarketDatabase(DatabaseDriverFactory().create()))
    }

    private val cryptoMarketClient: CryptoMarketClient by lazy {
        KtorCryptoMarketClient(HttpClientFactory().create())
    }

    private val cryptoMarketRepository: CryptoMarketRepository by lazy {
        CryptoMarketRepositoryImpl(
            cryptoMarketClient,
            cryptoMarketDataSource
        )
    }

    val readCryptoMarketUseCase: ReadCryptoMarketUseCase by lazy {
        ReadCryptoMarketUseCase(cryptoMarketRepository)
    }
}