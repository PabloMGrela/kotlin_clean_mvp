package com.grela.clean.model

import com.grela.clean.R
import com.grela.domain.model.LeagueDomainEntity

data class LeagueViewModel(
    val logoPath: String,
    val name: String,
    val icon: Int?
)

fun List<LeagueDomainEntity>.toLeagueViewModelList() = map { it.toLeagueViewModel() }

fun LeagueDomainEntity.toLeagueViewModel() = LeagueViewModel(logo, name, if (isCup) R.drawable.trophy else null)