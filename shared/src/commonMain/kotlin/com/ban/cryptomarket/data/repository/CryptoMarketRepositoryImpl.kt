package com.ban.cryptomarket.data.repository

import com.ban.cryptomarket.core.domain.util.CommonFlow
import com.ban.cryptomarket.data.local.CryptoMarketDataSource
import com.ban.cryptomarket.data.model.CryptocurrencyDto
import com.ban.cryptomarket.data.remote.CryptoMarketClient
import com.ban.cryptomarket.cryptomarket.data.repository.CryptoMarketRepository
import com.ban.cryptomarket.cryptomarket.domain.CryptoMarketException
import com.ban.cryptomarket.data.model.CryptocurrencyItem

class CryptoMarketRepositoryImpl(
    private val client: CryptoMarketClient,
    private val dataSource: CryptoMarketDataSource
) : CryptoMarketRepository {

    override suspend fun getCryptoMarket(): CommonFlow<List<CryptocurrencyItem>> {
        // Fetch cryptocurrencies from remote source
        val cryptocurrencies = try {
            client.fetchCryptocurrencies()
        } catch (e: CryptoMarketException) {
            emptyList<CryptocurrencyDto>()
        } catch (e: Exception) {
            emptyList<CryptocurrencyDto>()
        }

        // Clear cache if we have new data
        if (cryptocurrencies.isNotEmpty()) {
            dataSource.deleteAll()
        }

        // Cache cryptocurrencies
        cryptocurrencies.forEach {
            dataSource.insertCryptocurrencyItem(
                CryptocurrencyItem(
                    id = null,
                    name = it.name,
                    code = it.code,
                    value = it.value
                )
            )
        }

        // Return cached cryptocurrency list
        return dataSource.getMarket()
    }
}