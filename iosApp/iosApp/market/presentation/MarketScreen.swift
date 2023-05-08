//
//  MarketScreen.swift
//  iosApp
//
//  Created by Ban Markovic on 28.4.23..
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared

struct MarketScreen: View {
    private var readCryptoMarketUseCase: ReadCryptoMarketUseCase
    @ObservedObject var viewModel: IOSMarketViewModel
    
    init(readCryptoMarketUseCase: ReadCryptoMarketUseCase) {
        self.readCryptoMarketUseCase = readCryptoMarketUseCase
        self.viewModel = IOSMarketViewModel(readCryptoMarketUseCase: readCryptoMarketUseCase)
    }
    
    var body: some View {
        VStack(alignment: .center) {
            if viewModel.state.isLoading == true {
                ProgressView().padding(.vertical, 12)
            }
            List{
                ForEach(viewModel.state.cryptocurrencies, id: \.self.code) { cryptocurrencyItem in
                    CryptoItem(cryptocurrency: cryptocurrencyItem)
                }
            }
        }
        .onAppear {
            viewModel.startObserving()
            viewModel.onEvent(event: CryptoMarketEvent.Init())
        }
        .onDisappear {
            viewModel.dispose()
        }
    }
}
