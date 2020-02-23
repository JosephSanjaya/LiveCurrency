package com.sanjayajoseph.livecurrency.api.models.currencies.entities


/*
* @author Joseph Sanjaya on 2/22/2020.
* Linkedin : https://www.linkedin.com/in/josephsanjaya/
* Github : https://github.com/JosephSanjaya
*/

data class CurrenciesData(
    var country: String? = null,
    var flag: String? = null,
    var symbols: String? = null,
    var value: Double? = null
)