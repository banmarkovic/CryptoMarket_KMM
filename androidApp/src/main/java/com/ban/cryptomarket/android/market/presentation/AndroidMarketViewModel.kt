package com.ban.cryptomarket.android.market.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ban.cryptomarket.cryptomarket.domain.ReadCryptoMarketUseCase
import com.ban.cryptomarket.cryptomarket.presentation.CryptoMarketEvent
import com.ban.cryptomarket.cryptomarket.presentation.CryptoMarketViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AndroidMarketViewModel @Inject constructor(
    private val readCryptoMarketUseCase: ReadCryptoMarketUseCase
) : ViewModel() {

    private val viewModel by lazy { CryptoMarketViewModel(readCryptoMarketUseCase, viewModelScope) }

    val state = viewModel.state

    fun onEvent(event: CryptoMarketEvent) {
        viewModel.onEvent(event)
    }
}