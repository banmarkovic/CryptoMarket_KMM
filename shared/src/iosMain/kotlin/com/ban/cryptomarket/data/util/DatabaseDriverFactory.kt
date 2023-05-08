package com.ban.cryptomarket.data.util

import com.ban.cryptomarket.database.MarketDatabase
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver

actual class DatabaseDriverFactory {
    actual fun create(): SqlDriver {
        return NativeSqliteDriver(MarketDatabase.Schema, "market.db")
    }
}