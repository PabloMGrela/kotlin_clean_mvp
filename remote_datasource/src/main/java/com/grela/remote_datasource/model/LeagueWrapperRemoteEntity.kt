package com.grela.remote_datasource.model

import com.google.gson.annotations.SerializedName
import com.grela.data.model.LeagueDataEntity

data class LeagueWrapperRemoteEntity(
    @SerializedName("data") val leagues: List<LeagueRemoteEntity>
)

data class LeagueRemoteEntity(
    @SerializedName("id") val id: Int,
    @SerializedName("type") val type: String,
    @SerializedName("logo_path") val logo: String,
    @SerializedName("name") val name: String,
    @SerializedName("is_cup") val isCup: Boolean
)

fun LeagueRemoteEntity.toLeagueDataEntity() = LeagueDataEntity(id, type, logo, name, isCup)

fun List<LeagueRemoteEntity>.toLeagueDataEntityList() = map { it.toLeagueDataEntity() }