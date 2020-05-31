package com.grela.data.datasource

import com.grela.data.model.CountryDataEntity
import com.grela.domain.DataResult

interface SportRemoteDataSourceContract {
    fun getSport(): DataResult<Error, List<CountryDataEntity>>
}