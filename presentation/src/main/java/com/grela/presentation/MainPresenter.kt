package com.grela.presentation

import com.grela.domain.DataResult
import com.grela.domain.interactor.GetCountryUseCase
import com.grela.domain.interactor.Invoker
import com.grela.domain.model.CountryDomainEntity

class MainPresenter(
    private val view: MainViewTranslator,
    private val getCountryUseCase: GetCountryUseCase,
    private val invoker: Invoker
) : BasePresenter() {

    fun onCreate() {
        getCountries()
    }

    private fun getCountries() {
        invoker.execute(getCountryUseCase) {
            when (it) {
                is DataResult.Success -> view.showCountry(it.r)
                else -> view.showError()
            }
        }
    }
}

interface MainViewTranslator {
    fun showError()
    fun showCountry(r: CountryDomainEntity)
}