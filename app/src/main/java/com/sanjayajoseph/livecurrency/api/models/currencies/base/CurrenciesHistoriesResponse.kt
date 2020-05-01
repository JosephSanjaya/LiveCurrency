package com.sanjayajoseph.livecurrency.api.models.currencies.base

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.ObjectMapper
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

data class CurrenciesHistoriesResponse(

    @field:JsonProperty("start_at")
    var startDate: String? = null,

    @field:JsonProperty("end_at")
    var endDate: String? = null,

    @field:JsonProperty("rates")
    var currenciesRates: HashMap<String, CurrenciesRates>? = null,

    @field:JsonProperty("base")
    var base: String? = null
)