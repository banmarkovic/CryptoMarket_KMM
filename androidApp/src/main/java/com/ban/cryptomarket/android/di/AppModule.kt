package com.ban.cryptomarket.android.di

import android.app.Application
import com.ban.cryptomarket.cryptomarket.data.repository.CryptoMarketRepository
import com.ban.cryptomarket.cryptomarket.domain.ReadCryptoMarketUseCase
import com.ban.cryptomarket.data.local.CryptoMarketDataSource
import com.ban.cryptomarket.data.local.SqlDelightCryptoMarketDataSource
import com.ban.cryptomarket.data.remote.CryptoMarketClient
import com.ban.cryptomarket.data.remote.KtorCryptoMarketClient
import com.ban.cryptomarket.data.repository.CryptoMarketRepositoryImpl
import com.ban.cryptomarket.data.util.DatabaseDriverFactory
import com.ban.cryptomarket.data.util.HttpClientFactory
import com.ban.cryptomarket.database.MarketDatabase
import com.squareup.sqldelight.db.SqlDriver
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideHttpClient(): HttpClient {
        return HttpClientFactory().create()
    }

    @Provides
    @Singleton
    fun provideCryptoMarketClient(httpClient: HttpClient): CryptoMarketClient {
        return KtorCryptoMarketClient(httpClient)
    }

    @Provides
    @Singleton
    fun provideDatabaseDriver(app: Application): SqlDriver {
        return DatabaseDriverFactory(app).create()
    }

    @Provides
    @Singleton
    fun provideCryptoMarketDataSource(driver: SqlDriver): CryptoMarketDataSource {
        return SqlDelightCryptoMarketDataSource(MarketDatabase(driver))
    }

    @Provides
    fun provideCryptoMarketRepository(
        client: CryptoMarketClient,
        dataSource: CryptoMarketDataSource
    ): CryptoMarketRepository = CryptoMarketRepositoryImpl(client, dataSource)

    @Provides
    fun provideReadCryptoMarketUseCase(repository: CryptoMarketRepository): ReadCryptoMarketUseCase {
        return ReadCryptoMarketUseCase(repository)
    }
}