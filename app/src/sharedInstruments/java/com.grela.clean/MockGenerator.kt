package com.grela.clean

import com.grela.remote_datasource.model.CountryRemoteEntity
import com.grela.remote_datasource.model.ExtraInfoRemoteEntity
import com.grela.remote_datasource.model.LeagueRemoteEntity
import com.grela.remote_datasource.model.LeagueWrapperRemoteEntity

object MockGenerator {

    fun givenACountryRemoteEntity(imageUrl: String = "image"): CountryRemoteEntity = CountryRemoteEntity(
        "countryName",
        imageUrl,
        ExtraInfoRemoteEntity(
            "continent",
            "fifa",
            1.0f,
            0.0f
        ),
        LeagueWrapperRemoteEntity(
            createList(3) { givenALeagueRemoteEntity(it) }
        )
    )

    private fun givenALeagueRemoteEntity(id: Int) = LeagueRemoteEntity(
        id,
        "type",
        "logo",
        "name",
        true
    )

    fun <T> createList(numberOfItems: Int, givenFunction: (id: Int) -> T): List<T> {
        val list = mutableListOf<T>()
        for (i in 0 until numberOfItems) {
            list.add(i, givenFunction.invoke(i))
        }
        return list
    }

}