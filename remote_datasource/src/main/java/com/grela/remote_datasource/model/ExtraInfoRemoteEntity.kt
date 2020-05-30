package com.grela.remote_datasource.model

import com.google.gson.annotations.SerializedName
import com.grela.data.model.ExtraInfoDataEntity

data class ExtraInfoRemoteEntity(
    @SerializedName("continent") val continent: String,
    @SerializedName("fifa") val fifa: String,
    @SerializedName("longitude") val lon: Float,
    @SerializedName("latitude") val lat: Float
)

fun ExtraInfoRemoteEntity.toExtraInfoDataEntity() = ExtraInfoDataEntity(continent, fifa, lon, lat)
