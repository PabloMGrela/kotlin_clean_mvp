package com.grela.remote_datasource

import com.grela.data.model.CountryDataEntity
import com.grela.domain.DataResult
import com.grela.remote_datasource.model.CountryRemoteEntity
import com.grela.remote_datasource.model.toDataEntity
import retrofit2.Call
import retrofit2.http.GET
import java.io.IOException

interface SportApi {

    companion object {
        val baseUrl = "http://192.168.0.27:8989/"
    }

    @GET("db")
    fun getData(): Call<CountryRemoteEntity>

}

inline fun safeCall(
    block: () -> Call<CountryRemoteEntity>
): DataResult<Error, CountryDataEntity> =
    try {
        val result = block()
        val response = result.execute()
        if (response.isSuccessful) {
            response.body()?.let {
                DataResult.Success(it.toDataEntity())
            } ?: DataResult.Error(Error())
        } else {
            DataResult.Error(Error())
        }
    } catch (exception: IOException) {
        DataResult.Error(Error())
    }