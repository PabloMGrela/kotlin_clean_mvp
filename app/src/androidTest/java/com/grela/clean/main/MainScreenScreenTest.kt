package com.grela.clean.main

import androidx.test.rule.ActivityTestRule
import com.grela.clean.BaseUITestRunner
import com.grela.clean.MainActivity
import com.grela.clean.MockGenerator.createList
import com.grela.clean.MockGenerator.givenACountryRemoteEntity
import com.grela.clean.RemoteGenerator
import com.grela.clean.ScreenshotInstruments.takeScreenShot
import com.grela.clean.main.MainScreenRobot.Companion.mainScreenRobot
import com.grela.data.datasource.SportRemoteDataSourceContract
import com.grela.data.model.CountryDataEntity
import com.grela.remote_datasource.model.toDataEntity
import org.junit.Rule
import org.junit.Test
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

class MainScreenScreenTest : BaseUITestRunner() {

    private var countryList: List<CountryDataEntity> = emptyList()

    @get: Rule
    val mainActivityRule = object : ActivityTestRule<MainActivity>(MainActivity::class.java, false, false) {
        override fun beforeActivityLaunched() {
            super.beforeActivityLaunched()
            loadKoinModules(listOf(module {
                factory<SportRemoteDataSourceContract>(override = true) { RemoteGenerator.givenARemoteSportDataSource(countryList) }
            }))
        }
    }

    private fun init(
        countryList: List<CountryDataEntity> = createList(3) { givenACountryRemoteEntity().toDataEntity() }
    ) {
        this.countryList = countryList
        mainActivityRule.launchActivity(null)
    }


    @Test
    fun given_a_country_list_correct_content_is_shown() {
        init()
        mainScreenRobot {
            screenIsShown()
        }
    }

    @Test
    fun given_a_country_list_screenshot_is_correct() {
        init()
        takeScreenShot(mainActivityRule, "Main activity")
    }

}