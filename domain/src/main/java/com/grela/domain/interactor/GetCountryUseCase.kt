package com.grela.domain.interactor

import com.grela.domain.SportRepositoryContract
import com.grela.domain.model.CountryDomainEntity


class GetCountryUseCase(private val repository: SportRepositoryContract) : UseCase<List<CountryDomainEntity>>() {
    override suspend fun run(listener: Callback<Error, List<CountryDomainEntity>>) {
        listener.notify(repository.getCountry())
    }

}
