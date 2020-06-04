package com.grela.remote_datasource

import com.grela.domain.DataResult
import com.grela.remote_datasource.model.CountryRemoteEntity
import retrofit2.Call
import retrofit2.http.GET
import java.io.IOException

interface SportApi {

    companion object {
        val baseUrl = "http://192.168.1.33:8989/"
    }

    @GET("db")
    fun getData(): Call<List<CountryRemoteEntity>>

}

inline fun <T, R> safeCall(
    call: () -> Call<R>,
    transform: (R) -> T
): DataResult<Error, T> =
    try {
        val result = call()
        val response = result.execute()
        if (response.isSuccessful) {
            response.body()?.let {
                DataResult.Success(transform(it))
            } ?: DataResult.Error(Error())
        } else {
            DataResult.Error(Error())
        }
    } catch (exception: IOException) {
        DataResult.Error(Error())
    }