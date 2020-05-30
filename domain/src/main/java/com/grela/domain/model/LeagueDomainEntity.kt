package com.grela.domain.model

data class LeagueDomainEntity(
    val id: Int,
    val type: String,
    val logo: String,
    val name: String,
    val isCup: Boolean
)