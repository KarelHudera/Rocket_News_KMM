package com.example.rocketnews.di

import com.example.rocketnews.data_cache.CacheDataImp
import com.example.rocketnews.data_cache.ICacheData
import com.example.rocketnews.data_cache.sqldelight.SharedDatabase
import com.example.rocketnews.data_remote.IRemoteData
import com.example.rocketnews.data_remote.RemoteDataImp
import com.example.rocketnews.data_remote.model.mapper.ApiNewsMapper
import com.example.rocketnews.data_remote.model.mapper.ApiRocketMapper
import com.example.rocketnews.domain.IRepository
import com.example.rocketnews.domain.RepositoryImp
import com.example.rocketnews.domain.interactors.GetNewsUseCase
import com.example.rocketnews.domain.interactors.GetRocketUseCase
import com.example.rocketnews.domain.interactors.GetRocketsFavoritesUseCase
import com.example.rocketnews.domain.interactors.GetRocketsUseCase
import com.example.rocketnews.domain.interactors.IsRocketFavoriteUseCase
import com.example.rocketnews.domain.interactors.SwitchRocketFavoriteUseCase
import com.example.rocketnews.presentation.ui.screens.news.NewsViewModel
import com.example.rocketnews.presentation.ui.screens.rocketDetail.RocketDetailViewModel
import com.example.rocketnews.presentation.ui.screens.rockets.RocketsViewModel
import com.example.rocketnews.presentation.ui.screens.rocketsFavourite.RocketsFavoritesViewModel
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.Dispatchers
import kotlinx.serialization.json.Json
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

fun initKoin(appDeclaration: KoinAppDeclaration = {}) =
    startKoin {
        appDeclaration()
        modules(
            viewModelModule,
            useCasesModule,
            repositoryModule,
            ktorModule,
            sqlDelightModule,
            mapperModule,
            dispatcherModule,
            platformModule()
        )
    }

val viewModelModule = module {
    factory { RocketsViewModel(get()) }
    factory { RocketsFavoritesViewModel(get()) }
    factory { params -> RocketDetailViewModel(get(), get(), get(), params.get()) }
    factory { NewsViewModel(get()) }

}

val useCasesModule: Module = module {
    factory { GetRocketsUseCase(get(), get()) }
    factory { GetRocketsFavoritesUseCase(get(), get()) }
    factory { GetRocketUseCase(get(), get()) }
    factory { IsRocketFavoriteUseCase(get(), get()) }
    factory { SwitchRocketFavoriteUseCase(get(), get()) }
    factory { GetNewsUseCase(get(), get()) }

}

val repositoryModule = module {
    single<IRepository> { RepositoryImp(get(), get()) }
    single<ICacheData> { CacheDataImp(get()) }
    single<IRemoteData> { RemoteDataImp(get(), get(), get()) }
}

val ktorModule = module {
    single {
        HttpClient {
            install(ContentNegotiation) {
                json(
                    Json {
                        ignoreUnknownKeys = true
                        prettyPrint = true
                        isLenient = true
                    }
                )
            }
            install(Logging) {
                logger = object : Logger {
                    override fun log(message: String) {
                        co.touchlab.kermit.Logger.d(tag = "KERMIT",
                            messageString = message)
                    }
                }
                level = LogLevel.ALL
            }
        }
    }

}

val sqlDelightModule = module {
    single { SharedDatabase(get()) }
}

val dispatcherModule = module {
    factory { Dispatchers.Default }
}

val mapperModule = module {
    factory { ApiRocketMapper() }
    factory { ApiNewsMapper() }
}

fun initKoin() = initKoin {}



