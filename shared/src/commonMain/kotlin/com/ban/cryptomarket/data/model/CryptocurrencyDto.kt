package com.ban.cryptomarket.data.model

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class CryptocurrencyDto(
    @SerialName("name") val name: String,
    @SerialName("code") val code: String,
    @SerialName("value") val value: Double
)