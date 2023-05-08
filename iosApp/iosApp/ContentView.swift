import SwiftUI
import shared

struct ContentView: View {
    
    private let appModule = AppModule()
    

	var body: some View {
        MarketScreen(
            readCryptoMarketUseCase: appModule.readCryptoMarketUseCase
        )
	}
}
