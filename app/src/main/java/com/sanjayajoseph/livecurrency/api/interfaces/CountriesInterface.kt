package com.sanjayajoseph.livecurrency.api.interfaces

import com.sanjayajoseph.livecurrency.api.models.countries.CountriesResponse
import com.sanjayajoseph.livecurrency.api.models.currencies.base.CurrenciesResponse


/*
* @author Joseph Sanjaya on 2/28/2020.
* Linkedin : https://www.linkedin.com/in/josephsanjaya/
* Github : https://github.com/JosephSanjaya
*/

interface CountriesInterface {

    suspend fun getCountriesByAlpha(
        alpha: String
    ): ApiResult<ArrayList<CountriesResponse>>

}
