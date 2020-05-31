package com.grela.clean.data

import com.grela.clean.MockGenerator.createList
import com.grela.clean.MockGenerator.givenACountryRemoteEntity
import com.grela.data.model.toCountryDomainEntity
import com.grela.domain.DataResult
import com.grela.domain.SportRepositoryContract
import com.grela.domain.model.CountryDomainEntity
import com.grela.remote_datasource.model.toDataEntity

object DataGenerator {

    enum class RepositoryStatus {
        SUCCESS,
        ERROR
    }

    fun givenASportRepository(
        status: RepositoryStatus
    ) = object : SportRepositoryContract {
        override fun getCountry(): DataResult<Error, List<CountryDomainEntity>> {
            return when (status) {
                RepositoryStatus.SUCCESS -> DataResult.Success(createList(2) { givenACountryRemoteEntity().toDataEntity().toCountryDomainEntity() })
                RepositoryStatus.ERROR -> DataResult.Error(Error())
            }
        }

    }

}