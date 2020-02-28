package com.sanjayajoseph.livecurrency.api.models.currencies.base

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.ObjectMapper

data class CurrenciesResponse(

    @field:JsonProperty("date")
    var date: String? = null,

    @field:JsonProperty("rates")
    var currenciesRates: CurrenciesRates? = null,

    @field:JsonProperty("base")
    var base: String? = null
) {
    public fun toJSON(): String {
        return ObjectMapper().writeValueAsString(this)
    }
}