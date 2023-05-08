package com.ban.cryptomarket.data.local

import com.ban.cryptomarket.core.domain.util.CommonFlow
import com.ban.cryptomarket.core.domain.util.toCommonFlow
import com.ban.cryptomarket.database.MarketDatabase
import com.ban.cryptomarket.data.model.toCryptocurrencyItem
import com.ban.cryptomarket.data.model.CryptocurrencyItem
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import kotlinx.coroutines.flow.map

class SqlDelightCryptoMarketDataSource(
    db: MarketDatabase
) : CryptoMarketDataSource {

    private val queries = db.marketQueries

    override fun getMarket(): CommonFlow<List<CryptocurrencyItem>> {
        return queries.getMarket()
            .asFlow()
            .mapToList()
            .map { market ->
                market.map {
                    it.toCryptocurrencyItem()
                }
            }
            .toCommonFlow()
    }

    override suspend fun insertCryptocurrencyItem(item: CryptocurrencyItem) {
        queries.insertCryptocurrencyEntity(item.id, item.name, item.code, item.value)
    }

    override suspend fun deleteAll() {
        queries.deleteAll()
    }
}