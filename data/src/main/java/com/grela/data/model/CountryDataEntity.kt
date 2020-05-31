package com.grela.data.model

import com.grela.domain.model.CountryDomainEntity

data class CountryDataEntity(
    val name: String,
    val image: String,
    val extraInfo: ExtraInfoDataEntity,
    val leagues: List<LeagueDataEntity>
)

fun CountryDataEntity.toCountryDomainEntity() =
    CountryDomainEntity(name, image, extraInfo.continent, extraInfo.fifa, extraInfo.lon, extraInfo.lat, leagues.toLeagueDomainEntityList())

fun List<CountryDataEntity>.toCountryDomainEntityList() = map { it.toCountryDomainEntity() }