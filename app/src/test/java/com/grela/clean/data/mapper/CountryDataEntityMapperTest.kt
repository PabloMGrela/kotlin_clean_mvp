package com.grela.clean.data.mapper

import com.grela.clean.BaseUnitTest
import com.grela.clean.MockGenerator
import com.grela.data.model.toCountryDomainEntity
import com.grela.domain.model.CountryDomainEntity
import com.grela.remote_datasource.model.toDataEntity
import org.assertj.core.api.Assertions
import org.junit.Test

class CountryDataEntityMapperTest : BaseUnitTest() {
    
    @Test
    fun `given country data entity, when mapped to country domain entity, then it is correct type`() {
        val unmappedInstance = MockGenerator.givenACountryRemoteEntity().toDataEntity()
        val mappedInstance: Any = unmappedInstance.toCountryDomainEntity()
        Assertions.assertThat(mappedInstance is CountryDomainEntity).isTrue()
    }

    @Test
    fun `given country data entity, when mapped to country domain entity, then fields match`() {
        val unmappedInstance = MockGenerator.givenACountryRemoteEntity().toDataEntity()
        val mappedInstance = unmappedInstance.toCountryDomainEntity()
        Assertions.assertThat(unmappedInstance.name).isEqualTo(mappedInstance.name)
        Assertions.assertThat(unmappedInstance.image).isEqualTo(mappedInstance.image)
        Assertions.assertThat(unmappedInstance.extraInfo.continent).isEqualTo(mappedInstance.continent)
        Assertions.assertThat(unmappedInstance.extraInfo.fifa).isEqualTo(mappedInstance.fifa)
        Assertions.assertThat(unmappedInstance.extraInfo.lat).isEqualTo(mappedInstance.lat)
        Assertions.assertThat(unmappedInstance.extraInfo.lon).isEqualTo(mappedInstance.lon)
    }
}