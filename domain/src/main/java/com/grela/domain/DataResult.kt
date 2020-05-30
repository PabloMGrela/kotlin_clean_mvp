package com.grela.domain


sealed class DataResult<out L, out R> {

    data class Error<out L>(val l: L) : DataResult<L, Nothing>()

    data class Success<out R>(val r: R) : DataResult<Nothing, R>()

}