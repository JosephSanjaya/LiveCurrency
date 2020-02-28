package com.sanjayajoseph.livecurrency.api.repository

import com.sanjayajoseph.livecurrency.api.interfaces.*
import com.sanjayajoseph.livecurrency.api.models.countries.CountriesResponse


/*
* @author Joseph Sanjaya on 2/28/2020.
* Linkedin : https://www.linkedin.com/in/josephsanjaya/
* Github : https://github.com/JosephSanjaya
*/

class CountriesRepository(private val service: APICountriesInterface) : CountriesInterface {

    override suspend fun getCountriesByAlpha(alpha: String): ApiResult<ArrayList<CountriesResponse>> {
        return try {
            val result = service.getCountriesByAlphaAsync(alpha).await()
            ApiResult.Success(result)
        } catch (ex: Exception) {
            ApiResult.Error(ex)
        }
    }
}