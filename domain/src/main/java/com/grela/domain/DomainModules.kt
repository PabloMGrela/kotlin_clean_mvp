package com.grela.domain

import com.grela.domain.interactor.GetCountryUseCase
import com.grela.domain.interactor.Invoker
import com.grela.domain.interactor.UseCaseInvoker
import org.koin.dsl.module

object DomainModules {
    val modules = module {
        factory { GetCountryUseCase(get()) }
        factory<Invoker> { UseCaseInvoker() }
    }
}