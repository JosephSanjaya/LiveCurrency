package com.sanjayajoseph.livecurrency.api.models.currencies.base

import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.databind.ObjectMapper

data class CurrenciesHistoriesRates(
    @JsonIgnore
    var historiesRates: HashMap<String, CurrenciesRates>? = HashMap()
) {
    @JsonAnyGetter
    fun getHistoryRates(): HashMap<String, CurrenciesRates>? {
        return historiesRates
    }
    @JsonAnySetter
    fun setHistoryRates(key : String, value: CurrenciesRates) {
        historiesRates?.set(key, value)
    }
    public fun toJSON(): String {
        return ObjectMapper().writeValueAsString(this)
    }
}