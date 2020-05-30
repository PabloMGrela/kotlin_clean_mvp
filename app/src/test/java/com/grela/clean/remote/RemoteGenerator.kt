package com.grela.clean.remote

import com.grela.data.datasource.SportRemoteDataSourceContract
import com.grela.data.model.CountryDataEntity
import com.grela.domain.DataResult
import com.grela.remote_datasource.model.CountryRemoteEntity
import com.grela.remote_datasource.model.ExtraInfoRemoteEntity
import com.grela.remote_datasource.model.LeagueWrapperRemoteEntity

object RemoteGenerator {

    fun givenARemoteSportDataSource(
        country: CountryDataEntity? = null
    ) = object : SportRemoteDataSourceContract {
        override fun getSport(): DataResult<Error, CountryDataEntity> {
            return country?.let {
                DataResult.Success(it)
            } ?: DataResult.Error(Error())
        }
    }



}