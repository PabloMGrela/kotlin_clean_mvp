package com.grela.domain.interactor

interface IdleNotifier {
    fun increment(): Int

    @Throws(IllegalArgumentException::class)
    fun decrement()
}