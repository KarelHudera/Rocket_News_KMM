package com.example.rocketnews.data_cache.sqldelight

import app.cash.sqldelight.ColumnAdapter
import com.example.rocketnews.datacache.sqldelight.RocketFavorite

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

    private val listAdapter = object : ColumnAdapter<List<String>, String> {
        override fun decode(databaseValue: String) =
            if (databaseValue.isEmpty()) {
                listOf()
            } else {
                databaseValue.split(",")
            }
        override fun encode(value: List<String>) = value.joinToString(separator = ",")
    }

    private suspend fun initDatabase() {
        if (database == null) {
            database = AppDatabase.invoke(
                driverProvider.createDriver(),
                RocketFavorite.Adapter(listAdapter, boolAdapter, boolAdapter)
            )
        }
    }

    suspend operator fun <R> invoke(block: suspend (AppDatabase) -> R): R {
        initDatabase()
        return block(database!!)
    }
}