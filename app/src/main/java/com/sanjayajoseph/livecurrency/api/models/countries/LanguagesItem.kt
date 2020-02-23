package com.sanjayajoseph.livecurrency.api.models.countries

import com.fasterxml.jackson.annotation.JsonProperty

data class LanguagesItem(

	@field:JsonProperty("nativeName")
	val nativeName: String? = null,

	@field:JsonProperty("iso639_2")
	val iso6392: String? = null,

	@field:JsonProperty("name")
	val name: String? = null,

	@field:JsonProperty("iso639_1")
	val iso6391: String? = null
)