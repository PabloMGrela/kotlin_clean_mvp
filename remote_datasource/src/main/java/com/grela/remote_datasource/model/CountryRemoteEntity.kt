package com.grela.remote_datasource.model

import com.google.gson.annotations.SerializedName
import com.grela.data.model.CountryDataEntity

data class CountryRemoteEntity(
    @SerializedName("name") val name: String,
    @SerializedName("image_path") val image: String,
    @SerializedName("extra") val extraInfo: ExtraInfoRemoteEntity,
    @SerializedName("leagues") val leagueWrapper: LeagueWrapperRemoteEntity
)

fun CountryRemoteEntity.toDataEntity() = CountryDataEntity(name, image, extraInfo.toExtraInfoDataEntity(), leagueWrapper.leagues.toLeagueDataEntityList())

fun List<CountryRemoteEntity>.toDataEntityList() = map { it.toDataEntity() }