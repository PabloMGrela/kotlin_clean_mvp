package com.grela.data.repository

import com.grela.data.datasource.SportRemoteDataSourceContract
import com.grela.data.model.toCountryDomainEntityList
import com.grela.domain.DataResult
import com.grela.domain.SportRepositoryContract
import com.grela.domain.model.CountryDomainEntity

class SportRepository(
    private val sportRemoteDataSource: SportRemoteDataSourceContract
) : SportRepositoryContract {
    override fun getCountry(): DataResult<Error, List<CountryDomainEntity>> {
        return when (val result = sportRemoteDataSource.getSport()) {
            is DataResult.Error -> result
            is DataResult.Success -> DataResult.Success(result.r.toCountryDomainEntityList())
        }
    }
}