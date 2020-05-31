package com.grela.clean.data

import com.grela.clean.BaseUnitTest
import com.grela.clean.data.DataGenerator.givenASportRepository
import com.grela.domain.DataResult
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class SportRepositoryTest : BaseUnitTest() {

    @Test
    fun `given a repository, when try to get countries, then success result`() {
        val sportRepository = givenASportRepository(DataGenerator.RepositoryStatus.SUCCESS)
        val result = sportRepository.getCountry()

        assertThat(result is DataResult.Success).isTrue()
    }

    @Test
    fun `given a repository, when try to get countries, then error result`() {
        val sportRepository = givenASportRepository(DataGenerator.RepositoryStatus.ERROR)
        val result = sportRepository.getCountry()

        assertThat(result is DataResult.Error).isTrue()
    }
}