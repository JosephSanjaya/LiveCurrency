package com.sanjayajoseph.livecurrency.api.models.countries

import com.fasterxml.jackson.annotation.JsonProperty

data class Translations(

    @field:JsonProperty("br")
    val br: String? = null,

    @field:JsonProperty("de")
    val de: String? = null,

    @field:JsonProperty("pt")
    val pt: String? = null,

    @field:JsonProperty("ja")
    val ja: String? = null,

    @field:JsonProperty("hr")
    val hr: String? = null,

    @field:JsonProperty("it")
    val it: String? = null,

    @field:JsonProperty("fa")
    val fa: String? = null,

    @field:JsonProperty("fr")
    val fr: String? = null,

    @field:JsonProperty("es")
    val es: String? = null,

    @field:JsonProperty("nl")
    val nl: String? = null
)