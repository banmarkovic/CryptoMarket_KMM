package com.ban.cryptomarket.data.remote

import com.ban.cryptomarket.data.model.CryptocurrencyDto
import com.ban.cryptomarket.cryptomarket.domain.CryptoMarketError
import com.ban.cryptomarket.cryptomarket.domain.CryptoMarketException
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.utils.io.errors.*

class KtorCryptoMarketClient(
    private val httpClient: HttpClient
) : CryptoMarketClient {

    override suspend fun fetchCryptocurrencies(): List<CryptocurrencyDto> {
        val result = try {
            httpClient.get {
                url("https://run.mocky.io/v3/af3c93cc-a4b9-4a87-beab-455c3db31303")
                contentType(ContentType.Application.Json)
            }
        } catch (e: IOException) {
            throw CryptoMarketException(CryptoMarketError.SERVICE_UNAVAILABLE)
        }

        when (result.status.value) {
            in 200..299 -> Unit
            in 400..499 -> throw CryptoMarketException(CryptoMarketError.CLIENT_ERROR)
            500 -> throw CryptoMarketException(CryptoMarketError.SERVER_ERROR)
            else -> throw CryptoMarketException(CryptoMarketError.UNKNOWN_ERROR)
        }

        return try {
            result.body<List<CryptocurrencyDto>>()
        } catch (e: Exception) {
            throw CryptoMarketException(CryptoMarketError.CLIENT_ERROR)
        }
    }
}