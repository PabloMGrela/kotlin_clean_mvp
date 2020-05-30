package com.grela.domain.model

data class CountryDomainEntity(
    val name: String,
    val image: String,
    val continent: String,
    val fifa: String,
    val lon: Float,
    val lat: Float,
    val leagueWrapper: List<LeagueDomainEntity>
)
