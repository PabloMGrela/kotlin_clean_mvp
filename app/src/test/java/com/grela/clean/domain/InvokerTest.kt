package com.grela.clean.domain

import com.grela.clean.InvokerInstruments
import com.grela.clean.UseCaseInstruments
import com.grela.domain.DataResult
import kotlinx.coroutines.InternalCoroutinesApi
import org.junit.Test

@InternalCoroutinesApi
class InvokerTest {

    private val delay = 1_000L
    private val stringValue = "String"

    @Test
    fun `given one use case, when executed, then one success result`() {

        val mockInvoker = InvokerInstruments.givenAnInvoker()
        val mockUseCase = UseCaseInstruments.givenAStringSuccessResultUseCase(stringValue) { result ->
            assert(result is DataResult.Success)
            assert((result as DataResult.Success).r == stringValue)
        }

        mockInvoker.execute(mockUseCase)
    }


    @Test
    fun `given long blocking operation use case, when executed, then success result`() {
        val mockInvoker = InvokerInstruments.givenAnInvoker()
        val mockUseCase = UseCaseInstruments.givenAGenericLongRunningBlockingTaskUseCase(delay) { result ->
            assert(result is DataResult.Success)
        }

        mockInvoker.execute(mockUseCase)
    }

    @Test
    fun `given long suspending operation use case, when executed, then job is cancelled`() {
        val mockInvoker = InvokerInstruments.givenAnInvoker()
        val mockUseCase = UseCaseInstruments.givenAGenericLongRunningSuspendingTaskUseCase(delay) {}

        mockInvoker.execute(mockUseCase)
        mockInvoker.cancelTasks()
        assert(!mockInvoker.hasPendingTasks())
    }

    @Test
    fun `given long suspending operation use case, when executed, then job is cancelled and listener is not invoked`() {
        var callbackFlag = false
        val mockInvoker = InvokerInstruments.givenAnInvoker()
        val mockUseCase = UseCaseInstruments.givenAGenericLongRunningSuspendingTaskUseCase(delay) {
            callbackFlag = true
        }

        mockInvoker.execute(mockUseCase)
        mockInvoker.cancelTasks()
        assert(!mockInvoker.hasPendingTasks())
        assert(!callbackFlag)
    }


    @Test
    fun `given a use case running, then we have pending tasks`() {
        val mockInvoker = InvokerInstruments.givenAnInvoker()
        val mockUseCase = UseCaseInstruments.givenAGenericLongRunningSuspendingTaskUseCase(delay) {}

        mockInvoker.execute(mockUseCase)
        assert(mockInvoker.hasPendingTasks())
    }


    @Test
    fun `given a use case to run in background, then we receive the result in the main thread`() {
        val mockInvoker = InvokerInstruments.givenAnInvoker()
        val mockUseCase = UseCaseInstruments.givenAStringSuccessResultUseCase(stringValue) {
            assert(Thread.currentThread().name.contains("main"))
        }
        mockInvoker.execute(
            mockUseCase
        )
    }
}