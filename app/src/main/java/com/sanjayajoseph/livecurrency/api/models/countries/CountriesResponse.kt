package com.sanjayajoseph.livecurrency.api.models.countries

import com.fasterxml.jackson.annotation.JsonProperty

data class CountriesResponse(

	@field:JsonProperty("area")
	val area: Int? = null,

	@field:JsonProperty("nativeName")
	val nativeName: String? = null,

	@field:JsonProperty("capital")
	val capital: String? = null,

	@field:JsonProperty("demonym")
	val demonym: String? = null,

	@field:JsonProperty("flag")
	val flag: String? = null,

	@field:JsonProperty("alpha2Code")
	val alpha2Code: String? = null,

	@field:JsonProperty("languages")
	val languages: List<LanguagesItem?>? = null,

	@field:JsonProperty("borders")
	val borders: List<String?>? = null,

	@field:JsonProperty("subregion")
	val subregion: String? = null,

	@field:JsonProperty("callingCodes")
	val callingCodes: List<String?>? = null,

	@field:JsonProperty("regionalBlocs")
	val regionalBlocs: List<RegionalBlocsItem?>? = null,

	@field:JsonProperty("gini")
	val gini: Double? = null,

	@field:JsonProperty("population")
	val population: Int? = null,

	@field:JsonProperty("numericCode")
	val numericCode: String? = null,

	@field:JsonProperty("alpha3Code")
	val alpha3Code: String? = null,

	@field:JsonProperty("topLevelDomain")
	val topLevelDomain: List<String?>? = null,

	@field:JsonProperty("timezones")
	val timezones: List<String?>? = null,

	@field:JsonProperty("cioc")
	val cioc: String? = null,

	@field:JsonProperty("translations")
	val translations: Translations? = null,

	@field:JsonProperty("name")
	val name: String? = null,

	@field:JsonProperty("altSpellings")
	val altSpellings: List<String?>? = null,

	@field:JsonProperty("region")
	val region: String? = null,

	@field:JsonProperty("latlng")
	val latlng: List<Int?>? = null,

	@field:JsonProperty("currencies")
	val currencies: List<CurrenciesItem?>? = null
)