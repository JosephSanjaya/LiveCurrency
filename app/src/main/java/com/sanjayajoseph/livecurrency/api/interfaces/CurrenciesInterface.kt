package com.sanjayajoseph.livecurrency.api.interfaces

import com.sanjayajoseph.livecurrency.api.models.currencies.base.CurrenciesResponse


/*
* @author Joseph Sanjaya on 2/28/2020.
* Linkedin : https://www.linkedin.com/in/josephsanjaya/
* Github : https://github.com/JosephSanjaya
*/

interface CurrenciesInterface {
    suspend fun getCurrencies(
        base: String,
        symbols: String
    ): ApiResult<CurrenciesResponse>

    suspend fun getRatesByDate(
        date: String,
        base: String,
        symbols: String
    ): ApiResult<CurrenciesResponse>
}
