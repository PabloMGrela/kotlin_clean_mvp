package com.grela.clean.remote

import com.grela.clean.BaseUnitTest
import com.grela.clean.remote.ServerFixtures.enqueueServerError
import com.grela.clean.remote.ServerFixtures.enqueueServerFile
import com.grela.domain.DataResult
import com.grela.remote_datasource.Network
import com.grela.remote_datasource.SportApi
import com.grela.remote_datasource.SportRemoteDataSourceImplementation
import org.assertj.core.api.Assertions.assertThat
import org.junit.After
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.dsl.module

class SportRemoteDataSourceImplementationTest : BaseUnitTest() {
    private lateinit var mockServer: MockServer

    override fun setUp() {
        startKoin {
            modules(module {
                single(override = true) {
                    Network().provideApi(
                        mockServer.start(),
                        SportApi::class.java
                    )
                }
            })
        }
        mockServer = MockServer.create()
    }

    @After
    fun after() {
        mockServer.shutdown()
    }

    @Test
    fun `given server with success response, then we receive a success data result`() {
        enqueueServerFile(mockServer, ServerFixtures.SUCCESS_RESPONSE)
        assertThat(SportRemoteDataSourceImplementation().getSport() is DataResult.Success).isTrue()
    }

    @Test
    fun `given server with error response, then we receive an error data result`() {
        enqueueServerError(mockServer, 401)
        assertThat(SportRemoteDataSourceImplementation().getSport() is DataResult.Error).isTrue()
    }
}