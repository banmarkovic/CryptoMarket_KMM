package com.ban.cryptomarket.data.util

import android.content.Context
import com.ban.cryptomarket.database.MarketDatabase
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver

actual class DatabaseDriverFactory(
    private val context: Context
) {
    actual fun create(): SqlDriver {
        return AndroidSqliteDriver(MarketDatabase.Schema, context, "market.db")
    }
}