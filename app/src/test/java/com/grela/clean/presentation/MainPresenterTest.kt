package com.grela.clean.presentation

import com.grela.clean.BaseUnitTest
import com.grela.clean.presentation.PresenterGenerator.givenAMainPresenter
import com.grela.presentation.MainPresenter
import junit.framework.Assert.assertTrue
import org.junit.Test

class MainPresenterTest : BaseUnitTest() {

    private lateinit var presenter: MainPresenter
    private val mainPresenterCallbackResult = PresenterGenerator.MainPresenterCallbackResult()

    @Test
    fun `when get countries use case has success result, then show country method is called`() {
        presenter = givenAMainPresenter(mainPresenterCallbackResult, true)
        presenter.onCreate()

        assertTrue(mainPresenterCallbackResult.isMethodFired(PresenterGenerator.MainPresenterMethods.SHOW_COUNTRY))
    }

    @Test
    fun `when get countries use case has error result, then show error method is called`() {
        presenter = givenAMainPresenter(mainPresenterCallbackResult, false)
        presenter.onCreate()

        assertTrue(mainPresenterCallbackResult.isMethodFired(PresenterGenerator.MainPresenterMethods.SHOW_ERROR))
    }


}