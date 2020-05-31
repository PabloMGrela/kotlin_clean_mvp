package com.grela.domain

import com.grela.domain.model.CountryDomainEntity

interface SportRepositoryContract {

    fun getCountry(): DataResult<Error, List<CountryDomainEntity>>
}