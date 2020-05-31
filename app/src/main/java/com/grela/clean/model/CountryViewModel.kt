package com.grela.clean.model

import com.google.android.gms.maps.model.LatLng
import com.grela.domain.model.CountryDomainEntity

data class CountryViewModel(
    val name: String,
    val flag: String,
    val continent: String,
    val location: LatLng,
    val leagues: List<LeagueViewModel>
)

fun CountryDomainEntity.toCountryViewModel() = CountryViewModel(name, image, continent, LatLng(lat.toDouble(), lon.toDouble()), leagueWrapper.toLeagueViewModelList())