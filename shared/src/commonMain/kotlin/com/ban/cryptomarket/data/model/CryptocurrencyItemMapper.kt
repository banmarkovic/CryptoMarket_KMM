package com.ban.cryptomarket.data.model

import database.Market

fun Market.toCryptocurrencyItem(): CryptocurrencyItem {
    return CryptocurrencyItem(
        id = id,
        name = name,
        code = code,
        value = value_,
    )
}