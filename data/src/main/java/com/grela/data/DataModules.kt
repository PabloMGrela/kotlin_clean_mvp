package com.grela.data

import com.grela.data.repository.SportRepository
import com.grela.domain.SportRepositoryContract
import org.koin.dsl.module

object DataModules {

    val modules = module {
        factory<SportRepositoryContract> { SportRepository(get()) }
    }
}