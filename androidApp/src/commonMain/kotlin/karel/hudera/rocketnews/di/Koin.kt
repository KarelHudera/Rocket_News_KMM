package karel.hudera.rocketnews.di

import karel.hudera.rocketnews.data_cache.CacheDataImp
import karel.hudera.rocketnews.data_cache.sqldelight.SharedDatabase
import karel.hudera.rocketnews.data_remote.RemoteDataImp
import karel.hudera.rocketnews.data_remote.model.mapper.ApiNewsMapper
import karel.hudera.rocketnews.data_remote.model.mapper.ApiRocketMapper
import karel.hudera.rocketnews.data_remote.model.mapper.ApiSpaceFlightMapper
import karel.hudera.rocketnews.domain.IRepository
import karel.hudera.rocketnews.domain.interactors.GetNewsUseCase
import karel.hudera.rocketnews.domain.interactors.GetRocketUseCase
import karel.hudera.rocketnews.domain.interactors.GetRocketsFavoritesUseCase
import karel.hudera.rocketnews.domain.interactors.GetRocketsUseCase
import karel.hudera.rocketnews.domain.interactors.GetSpaceFlightNewUseCase
import karel.hudera.rocketnews.domain.interactors.GetSpaceFlightNewsUseCase
import karel.hudera.rocketnews.domain.interactors.IsRocketFavoriteUseCase
import karel.hudera.rocketnews.domain.interactors.SwitchRocketFavoriteUseCase
import karel.hudera.rocketnews.presentation.ui.screens.news.NewsViewModel
import karel.hudera.rocketnews.presentation.ui.screens.newsImage.NewsImageViewModel
import karel.hudera.rocketnews.presentation.ui.screens.rocketDetail.RocketDetailViewModel
import karel.hudera.rocketnews.presentation.ui.screens.rockets.RocketsViewModel
import karel.hudera.rocketnews.presentation.ui.screens.rocketsFavourite.RocketsFavoritesViewModel
import karel.hudera.rocketnews.presentation.ui.screens.spaceFlightNews.SpaceFlightNewsViewModel
import karel.hudera.rocketnews.presentation.ui.screens.spaceFlightNewsDetail.SpaceFlightNewsDetailViewModel
import karel.hudera.rocketnews.repository.ICacheData
import karel.hudera.rocketnews.repository.IRemoteData
import karel.hudera.rocketnews.repository.RepositoryImp
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
    factory { NewsImageViewModel() }
    factory { SpaceFlightNewsViewModel(get()) }
    factory { SpaceFlightNewsDetailViewModel(get(), get()) }
}

val useCasesModule: Module = module {
    factory { GetRocketsUseCase(get(), get()) }
    factory { GetRocketsFavoritesUseCase(get(), get()) }
    factory { GetRocketUseCase(get(), get()) }
    factory { IsRocketFavoriteUseCase(get(), get()) }
    factory { SwitchRocketFavoriteUseCase(get(), get()) }
    factory { GetNewsUseCase(get(), get()) }
    factory { GetSpaceFlightNewsUseCase(get(), get()) }
    factory { GetSpaceFlightNewUseCase(get(), get()) }
}

val repositoryModule = module {
    single<IRepository> { RepositoryImp(get(), get()) }
    single<ICacheData> { CacheDataImp(get()) }
    single<IRemoteData> { RemoteDataImp(get(), get(), get(), get()) }
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
    factory { ApiSpaceFlightMapper() }
}

fun initKoin() = initKoin {}



