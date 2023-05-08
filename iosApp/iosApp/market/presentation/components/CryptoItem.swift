//
//  CryptoItem.swift
//  iosApp
//
//  Created by Ban Markovic on 28.4.23..
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared

struct CryptoItem: View {
    var cryptocurrency: CryptocurrencyItem
    var body: some View {
        VStack(alignment: .leading) {
            Text(cryptocurrency.name).padding(.bottom, 2)
            Text(cryptocurrency.code).padding(.bottom, 2)
            Text(String(cryptocurrency.value) + " $").padding(.bottom, 12)
        }
    }
}

struct CryptoItem_Previews: PreviewProvider {
    static var previews: some View {
        CryptoItem(cryptocurrency: CryptocurrencyItem(
            id: nil, name: "Bitcoin", code: "BTC", value: 27068.0
        ))
    }
}
