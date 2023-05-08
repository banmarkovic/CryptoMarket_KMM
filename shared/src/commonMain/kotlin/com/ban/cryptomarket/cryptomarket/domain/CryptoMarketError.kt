package com.ban.cryptomarket.cryptomarket.domain

enum class CryptoMarketError {
    SERVICE_UNAVAILABLE,
    SERVER_ERROR,
    CLIENT_ERROR,
    UNKNOWN_ERROR
}

class CryptoMarketException(error: CryptoMarketError) : Exception(error.toString())