package com.grela.remote_datasource

import com.grela.data.datasource.SportRemoteDataSourceContract
import com.grela.data.model.CountryDataEntity
import com.grela.domain.DataResult
import org.koin.core.KoinComponent
import org.koin.core.inject

class SportRemoteDataSourceImplementation : SportRemoteDataSourceContract, KoinComponent {

    val api: SportApi by inject()

    override fun getSport(): DataResult<Error, List<CountryDataEntity>> {
        return safeCall { api.getData() }
    }
}