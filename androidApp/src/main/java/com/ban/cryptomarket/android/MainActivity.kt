package com.ban.cryptomarket.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ban.cryptomarket.android.market.presentation.AndroidMarketViewModel
import com.ban.cryptomarket.cryptomarket.presentation.CryptoMarketEvent
import com.ban.cryptomarket.cryptomarket.presentation.CryptoMarketState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: AndroidMarketViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.onEvent(CryptoMarketEvent.Init())

        setContent {
            val state = viewModel.state.collectAsState()

            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    CryptoMarket(state.value)
                }
            }
        }
    }
}

@Composable
fun CryptoMarket(state: CryptoMarketState) {
    LazyColumn(contentPadding = PaddingValues(16.dp)) {
        if (state.isLoading) {
            item {
                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }
        }
        items(state.cryptocurrencies.size) { index ->
            Column {
                Text(text = state.cryptocurrencies[index].name)
                Spacer(modifier = Modifier.height(2.dp))
                Text(text = state.cryptocurrencies[index].code)
                Spacer(modifier = Modifier.height(2.dp))
                Text(text = "${state.cryptocurrencies[index].value} $")
            }
            Spacer(modifier = Modifier.height(12.dp))
        }
    }
}
