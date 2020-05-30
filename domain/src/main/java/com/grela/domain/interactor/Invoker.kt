package com.grela.domain.interactor

import com.grela.domain.DataResult


interface Invoker {

    fun <T> execute(useCase: UseCase<T>, result: ((DataResult<*, T>) -> Unit)? = null)

    fun hasPendingTasks(): Boolean

    fun cancelTasks()
}