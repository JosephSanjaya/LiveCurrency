package com.sanjayajoseph.livecurrency.api.models.currencies.base

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.ObjectMapper

data class CurrenciesRates(

    @field:JsonProperty("HRK")
    var croatianCurrency: Double? = null,

    @field:JsonProperty("CHF")
    var swishCurrency: Double? = null,

    @field:JsonProperty("MXN")
    var mexicanCurrency: Double? = null,

    @field:JsonProperty("ZAR")
    var southAfricanCurrency: Double? = null,

    @field:JsonProperty("INR")
    var indianCurrecy: Double? = null,

    @field:JsonProperty("THB")
    var thaiCurrency: Double? = null,

    @field:JsonProperty("CNY")
    var chinaCurrency: Double? = null,

    @field:JsonProperty("AUD")
    var australianCurrency: Double? = null,

    @field:JsonProperty("ILS")
    var israelCurrency: Double? = null,

    @field:JsonProperty("KRW")
    var southKoreanCurrency: Double? = null,

    @field:JsonProperty("JPY")
    var japanCurrency: Double? = null,

    @field:JsonProperty("PLN")
    var polandCurrency: Double? = null,

    @field:JsonProperty("GBP")
    var britishCurrency: Double? = null,

    @field:JsonProperty("IDR")
    var indonesiaCurrency: Double? = null,

    @field:JsonProperty("HUF")
    var hungarianCurrency: Double? = null,

    @field:JsonProperty("PHP")
    var philippineCurrency: Double? = null,

    @field:JsonProperty("TRY")
    var turkishCurrency: Double? = null,

    @field:JsonProperty("RUB")
    var russianCurrency: Double? = null,

    @field:JsonProperty("HKD")
    var hongkongCurrency: Double? = null,

    @field:JsonProperty("ISK")
    var icelandicCurrency: Double? = null,

    @field:JsonProperty("EUR")
    var europeanCurrency: Double? = null,

    @field:JsonProperty("DKK")
    var danishCurrency: Double? = null,

    @field:JsonProperty("CAD")
    var canadianCurrency: Double? = null,

    @field:JsonProperty("MYR")
    var malaysianCurrency: Double? = null,

    @field:JsonProperty("USD")
    var usCurrency: Double? = null,

    @field:JsonProperty("BGN")
    var bulgarianCurrency: Double? = null,

    @field:JsonProperty("NOK")
    var norwegianCurrency: Double? = null,

    @field:JsonProperty("RON")
    var romanianCurrency: Double? = null,

    @field:JsonProperty("SGD")
    var singaporeCurrency: Double? = null,

    @field:JsonProperty("CZK")
    var czechCurrency: Double? = null,

    @field:JsonProperty("SEK")
    var swedishCurrency: Double? = null,

    @field:JsonProperty("NZD")
    var newZealandCurrency: Double? = null,

    @field:JsonProperty("BRL")
    var brazillianCurrency: Double? = null
) {
    public fun toJSON(): String {
        return ObjectMapper().writeValueAsString(this)
    }
}