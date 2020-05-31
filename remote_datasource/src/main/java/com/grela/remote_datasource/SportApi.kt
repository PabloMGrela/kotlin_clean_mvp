package com.grela.remote_datasource

import com.grela.data.model.CountryDataEntity
import com.grela.domain.DataResult
import com.grela.remote_datasource.model.CountryRemoteEntity
import com.grela.remote_datasource.model.toDataEntityList
import retrofit2.Call
import retrofit2.http.GET
import java.io.IOException

interface SportApi {

    companion object {
        val baseUrl = "http://192.168.0.27:8989/"
    }

    @GET("db")
    fun getData(): Call<List<CountryRemoteEntity>>

}

inline fun safeCall(
    block: () -> Call<List<CountryRemoteEntity>>
): DataResult<Error, List<CountryDataEntity>> =
    try {
        val result = block()
        val response = result.execute()
        if (response.isSuccessful) {
            response.body()?.let {
                DataResult.Success(it.toDataEntityList())
            } ?: DataResult.Error(Error())
        } else {
            DataResult.Error(Error())
        }
    } catch (exception: IOException) {
        DataResult.Error(Error())
    }