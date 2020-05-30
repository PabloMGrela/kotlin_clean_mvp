package com.grela.data.model

import com.grela.domain.model.LeagueDomainEntity

data class LeagueDataEntity(
    val id: Int,
    val type: String,
    val logo: String,
    val name: String,
    val isCup: Boolean
)

fun LeagueDataEntity.toLeagueDomainEntity() = LeagueDomainEntity(id, type, logo, name, isCup)

fun List<LeagueDataEntity>.toLeagueDomainEntityList() = map { it.toLeagueDomainEntity() }