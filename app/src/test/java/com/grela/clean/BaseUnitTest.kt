package com.grela.clean

import org.junit.Before
import org.koin.core.context.startKoin
import org.koin.test.AutoCloseKoinTest

open class BaseUnitTest : AutoCloseKoinTest() {
    @Before
    fun setUp() {
        startKoin {
           
        }
    }
}