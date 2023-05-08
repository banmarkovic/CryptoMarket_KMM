//
//  IOSMarketViewModel.swift
//  iosApp
//
//  Created by Ban Markovic on 28.4.23..
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared

extension MarketScreen {
    @MainActor class IOSMarketViewModel: ObservableObject {
        private var readCryptoMarketUseCase: ReadCryptoMarketUseCase
        
        private let viewModel: CryptoMarketViewModel
        
        @Published var state: CryptoMarketState = CryptoMarketState(
            cryptocurrencies: [],
                isLoading: false)
        
        private var handle: DisposableHandle?
        
        init(readCryptoMarketUseCase: ReadCryptoMarketUseCase) {
            self.readCryptoMarketUseCase = readCryptoMarketUseCase
            self.viewModel = CryptoMarketViewModel(readCryptoMarketUseCase: readCryptoMarketUseCase, coroutineScope: nil)
        }
        
        func onEvent(event: CryptoMarketEvent) {
            self.viewModel.onEvent(event: event)
        }
        
        func startObserving() {
            handle = viewModel.state.subscribe(onCollect: { state in
                if let state = state {
                    self.state = state
                }
            })
        }
        
        func dispose() {
            handle?.dispose()
        }
    }
}
