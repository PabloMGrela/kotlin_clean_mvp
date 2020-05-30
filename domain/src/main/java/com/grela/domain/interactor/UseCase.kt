package com.grela.domain.interactor

import com.grela.domain.DataResult


typealias Callback<L, R> = (DataResult<L, R>) -> Unit

abstract class UseCase<R> {

    abstract suspend fun run(listener: Callback<Error, R>)

    fun Callback<Error, R>.notify(result: DataResult<Error, R>) {
        this(result)
    }
}
