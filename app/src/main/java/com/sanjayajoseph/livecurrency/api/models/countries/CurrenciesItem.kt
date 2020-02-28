package com.sanjayajoseph.livecurrency.api.models.countries

import com.fasterxml.jackson.annotation.JsonProperty

data class CurrenciesItem(

    @field:JsonProperty("symbol")
    val symbol: String? = null,

    @field:JsonProperty("code")
    val code: String? = null,

    @field:JsonProperty("name")
    val name: String? = null
)