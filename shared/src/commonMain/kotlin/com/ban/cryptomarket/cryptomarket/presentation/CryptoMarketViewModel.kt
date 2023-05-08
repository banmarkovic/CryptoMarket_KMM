package com.ban.cryptomarket.cryptomarket.presentation

import com.ban.cryptomarket.core.domain.util.toCommonStateFlow
import com.ban.cryptomarket.cryptomarket.domain.ReadCryptoMarketUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CryptoMarketViewModel(
    private val readCryptoMarketUseCase: ReadCryptoMarketUseCase,
    private val coroutineScope: CoroutineScope?
) {

    private val viewModelScope = coroutineScope ?: CoroutineScope(Dispatchers.Main)

    private val _state = MutableStateFlow(CryptoMarketState())
    val state = _state.asStateFlow().toCommonStateFlow()

    fun onEvent(event: CryptoMarketEvent) {
        when (event) {
            is CryptoMarketEvent.Init -> init()
        }
    }

    private fun init() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            readCryptoMarketUseCase.execute().collect { cryptocurrencyItems ->
                _state.update { marketState ->
                    marketState.copy(
                        cryptocurrencies = cryptocurrencyItems,
                        isLoading = false
                    )
                }
            }
        }
    }
}