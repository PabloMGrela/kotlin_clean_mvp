package com.grela.clean.remote

import com.grela.data.datasource.SportRemoteDataSourceContract
import com.grela.data.model.CountryDataEntity
import com.grela.domain.DataResult

object RemoteGenerator {

    fun givenARemoteSportDataSource(
        country: List<CountryDataEntity>? = null
    ) = object : SportRemoteDataSourceContract {
        override fun getSport(): DataResult<Error, List<CountryDataEntity>> {
            return country?.let {
                DataResult.Success(it)
            } ?: DataResult.Error(Error())
        }
    }


}