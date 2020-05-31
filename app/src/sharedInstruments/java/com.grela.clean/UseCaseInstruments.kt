package com.grela.clean

import com.grela.domain.DataResult
import com.grela.domain.interactor.Callback
import com.grela.domain.interactor.UseCase
import kotlinx.coroutines.delay

object UseCaseInstruments {

    fun givenAStringSuccessResultUseCase(value: String, callbackResult: Callback<Error, String>) =
        object : UseCase<String>() {
            override suspend fun run(listener: Callback<Error, String>) {
                callbackResult(DataResult.Success(value))
            }
        }

    fun givenAGenericLongRunningBlockingTaskUseCase(value: Long, callbackResult: Callback<Error, String>) =
        object : UseCase<String>() {
            override suspend fun run(listener: Callback<Error, String>) {
                Thread.sleep(value)
                callbackResult(DataResult.Success("String"))
            }
        }

    fun givenAGenericLongRunningSuspendingTaskUseCase(value: Long, callbackResult: Callback<Error, String>) =
        object : UseCase<String>() {
            override suspend fun run(listener: Callback<Error, String>) {
                delay(value)
                callbackResult(DataResult.Success("String"))
            }
        }
}