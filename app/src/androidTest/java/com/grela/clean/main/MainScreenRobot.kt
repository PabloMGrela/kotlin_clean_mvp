package com.grela.clean.main

import com.grela.clean.R
import com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertDisplayed

class MainScreenRobot {

    companion object {
        infix fun mainScreenRobot(func: MainScreenRobot.() -> Unit) = MainScreenRobot().apply { func() }
    }

    fun screenIsShown() {
        assertDisplayed(R.id.countryListView)
    }
}