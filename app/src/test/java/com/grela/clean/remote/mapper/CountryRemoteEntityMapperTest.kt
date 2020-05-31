package com.grela.clean.remote.mapper

import com.grela.clean.BaseUnitTest
import com.grela.clean.MockGenerator.givenACountryRemoteEntity
import com.grela.data.model.CountryDataEntity
import com.grela.remote_datasource.model.toDataEntity
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class CountryRemoteEntityMapperTest : BaseUnitTest() {

    @Test
    fun `given country network response, when mapped to country data entity, then it is correct type`() {
        val unmappedInstance = givenACountryRemoteEntity()
        val mappedInstance: Any = unmappedInstance.toDataEntity()
        assertThat(mappedInstance is CountryDataEntity).isTrue()
    }

    @Test
    fun `given country network response, when mapped to country data entity, then fields match`() {
        val unmappedInstance = givenACountryRemoteEntity()
        val mappedInstance = unmappedInstance.toDataEntity()
        assertThat(unmappedInstance.name).isEqualTo(mappedInstance.name)
        assertThat(unmappedInstance.image).isEqualTo(mappedInstance.image)
        assertThat(unmappedInstance.extraInfo.continent).isEqualTo(mappedInstance.extraInfo.continent)
        assertThat(unmappedInstance.extraInfo.fifa).isEqualTo(mappedInstance.extraInfo.fifa)
        assertThat(unmappedInstance.extraInfo.lat).isEqualTo(mappedInstance.extraInfo.lat)
        assertThat(unmappedInstance.extraInfo.lon).isEqualTo(mappedInstance.extraInfo.lon)
    }
}