package com.example.rocketnews.data_cache.sqldelight

import app.cash.sqldelight.ColumnAdapter
import com.example.rocketnews.datacache.sqldelight.RocketFavorite
import com.example.rocketnews.domain.model.Rocket


class SharedDatabase(
    private val driverProvider: DatabaseDriverFactory,
) {
    private var database: AppDatabase? = null

    private val boolAdapter = object : ColumnAdapter<Boolean, String> {
        override fun decode(databaseValue: String): Boolean = when (databaseValue) {
            "true" -> true
            "false" -> false
            else -> false
        }

        override fun encode(value: Boolean): String = when (value) {
            true -> "true"
            false -> "false"
        }
    }

    private suspend fun initDatabase() {
        if (database == null) {
            database = AppDatabase.invoke(
                driverProvider.createDriver(),
                RocketFavorite.Adapter(boolAdapter, boolAdapter)
            )
        }
    }

    suspend operator fun <R> invoke(block: suspend (AppDatabase) -> R): R {
        initDatabase()
        return block(database!!)
    }
}