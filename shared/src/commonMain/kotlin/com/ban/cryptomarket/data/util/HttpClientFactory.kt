package com.ban.cryptomarket.data.util

import io.ktor.client.*

expect class HttpClientFactory {
    fun create(): HttpClient
}