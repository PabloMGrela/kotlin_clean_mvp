package com.grela.clean

abstract class BaseCallbackResult<T> {
    var firedMethods = mutableListOf<FiredMethod<T>>()

    fun isMethodFired(methodName: T, withAssertions: (FiredMethod<T>.() -> Boolean) = { true }): Boolean {
        return firedMethods.firstOrNull {
            it.method == methodName
        }?.let {
            withAssertions(it)
        } ?: false
    }

    fun putMethodCall(methodName: T, params: Any? = Unit) {
        firedMethods.add(FiredMethod(methodName, params ?: Unit))
    }

}

data class FiredMethod<T>(
    val method: T,
    val params: Any = Unit
)