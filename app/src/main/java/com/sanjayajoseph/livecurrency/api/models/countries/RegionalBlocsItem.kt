package com.sanjayajoseph.livecurrency.api.models.countries

import com.fasterxml.jackson.annotation.JsonProperty

data class RegionalBlocsItem(

    @field:JsonProperty("otherNames")
    val otherNames: List<String?>? = null,

    @field:JsonProperty("acronym")
    val acronym: String? = null,

    @field:JsonProperty("name")
    val name: String? = null,

    @field:JsonProperty("otherAcronyms")
    val otherAcronyms: List<Any?>? = null
)