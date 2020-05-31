package com.grela.clean.domain

import com.grela.clean.BaseUnitTest
import com.grela.clean.domain.DomainGenerator.givenAGetCountryUseCase
import com.grela.domain.DataResult
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class GetCountryUseCaseTest : BaseUnitTest() {

    @Test
    fun `when trying to get country with success result, we return success`() {
        val useCase = givenAGetCountryUseCase(true)
        runBlocking {
            useCase.run { result ->
                assertThat(result).isNotNull()
                assertThat(result is DataResult.Success).isTrue()
            }
        }
    }

    @Test
    fun `when trying to get country with error result, we return error`() {
        val useCase = givenAGetCountryUseCase(false)
        runBlocking {
            useCase.run { result ->
                assertThat(result).isNotNull()
                assertThat(result is DataResult.Error).isTrue()
            }
        }
    }
}