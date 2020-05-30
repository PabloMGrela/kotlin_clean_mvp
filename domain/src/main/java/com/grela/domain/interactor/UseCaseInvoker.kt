package com.grela.domain.interactor

import com.grela.domain.DataResult
import kotlinx.coroutines.*
import java.util.concurrent.atomic.AtomicInteger
import kotlin.coroutines.CoroutineContext

open class CoroutineContextProvider {
    open val main: CoroutineContext by lazy { Dispatchers.Main }
    open val background: CoroutineContext by lazy { Dispatchers.Default }
}

class UseCaseInvoker(private val contextProvider: CoroutineContextProvider = CoroutineContextProvider(), private val idleNotifier: IdleNotifier? = null) : Invoker {

    private var parentJob: Job? = null
    private var numberOfSimultaneousUseCases = AtomicInteger(0)
    private var numberOfUseCases = AtomicInteger(0)
    private val results = mutableMapOf<Int, Result<*>>()
    private var isCancelled = false

    @InternalCoroutinesApi
    override fun <T> execute(useCase: UseCase<T>, result: ((DataResult<*, T>) -> Unit)?) {
        launchAndCompletion {
            try {
                executeUseCase(useCase) {
                    result?.invoke(it as DataResult<*, T>)
                }
            } catch (ex: Exception){
                result?.invoke(DataResult.Error(Error(ex)))
            }
        }
    }

    override fun hasPendingTasks() = parentJob != null && parentJob!!.children.count() > 0

    override fun cancelTasks() {
        parentJob?.cancel()
        isCancelled = true
    }

    @InternalCoroutinesApi
    private fun launchAndCompletion(block: suspend CoroutineScope.() -> Unit) {
        idleNotifier?.increment()
        GlobalScope.launch(contextProvider.main, CoroutineStart.DEFAULT) {
            if (!isCancelled) {
                parentJob = coroutineContext[Job]
            }
            parentJob?.invokeOnCompletion(true, true) {
                if (it is CancellationException) {
                    numberOfUseCases = AtomicInteger(0)
                    numberOfSimultaneousUseCases = AtomicInteger(0)
                    results.clear()
                }
                idleNotifier?.decrement()
            } ?: idleNotifier?.decrement()
            block()
        }
    }

    @InternalCoroutinesApi
    private suspend fun executeUseCase(useCase: UseCase<*>, listener: (DataResult<*, *>) -> Unit) {
        val jobs = mutableListOf<Job>()
        jobs += GlobalScope.launch(kotlin.coroutines.coroutineContext + contextProvider.background, CoroutineStart.LAZY) {
            useCase.run { result ->
                launch(contextProvider.main) {
                    if (!isCancelled) {
                        listener(result)
                    }
                }
            }
        }

        jobs.forEach {
            it.join()
        }
    }
}