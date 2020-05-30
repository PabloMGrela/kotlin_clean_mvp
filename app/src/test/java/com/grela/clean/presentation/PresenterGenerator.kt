package com.grela.clean.presentation

import com.grela.clean.BaseCallbackResult
import com.grela.clean.domain.DomainGenerator.givenAGetCountryUseCase
import com.grela.clean.domain.InvokerInstruments.givenAnInvoker
import com.grela.domain.model.CountryDomainEntity
import com.grela.presentation.MainPresenter
import com.grela.presentation.MainViewTranslator

object PresenterGenerator {

    class MainPresenterCallbackResult : BaseCallbackResult<MainPresenterMethods>()

    enum class MainPresenterMethods {
        GET_COUNTRIES,
        SHOW_ERROR,
        SHOW_COUNTRY
    }

    fun givenAMainPresenter(mainPresenterCallbackResult: MainPresenterCallbackResult, isSuccess: Boolean) = MainPresenter(
        object : MainViewTranslator {
            override fun showError() {
                mainPresenterCallbackResult.putMethodCall(MainPresenterMethods.SHOW_ERROR)
            }

            override fun showCountry(r: CountryDomainEntity) {
                mainPresenterCallbackResult.putMethodCall(MainPresenterMethods.SHOW_COUNTRY)
            }
        },
        givenAGetCountryUseCase(isSuccess),
        givenAnInvoker()
    )

}