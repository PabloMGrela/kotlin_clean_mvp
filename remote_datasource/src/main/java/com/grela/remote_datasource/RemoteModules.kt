package com.grela.remote_datasource

import com.grela.data.datasource.SportRemoteDataSourceContract
import org.koin.dsl.module

object RemoteModules {
    val modules = module {
        single {
            Network().provideApi(
                SportApi.baseUrl,
                SportApi::class.java
            )
        }
        factory<SportRemoteDataSourceContract> { SportRemoteDataSourceImplementation() }
    }
}