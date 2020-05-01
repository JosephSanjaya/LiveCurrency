package com.sanjayajoseph.livecurrency.api.repository

import com.sanjayajoseph.livecurrency.api.interfaces.APIInterface
import com.sanjayajoseph.livecurrency.api.interfaces.ApiResult
import com.sanjayajoseph.livecurrency.api.interfaces.CurrenciesInterface
import com.sanjayajoseph.livecurrency.api.models.currencies.base.CurrenciesHistoriesResponse
import com.sanjayajoseph.livecurrency.api.models.currencies.base.CurrenciesResponse


/*
* @author Joseph Sanjaya on 2/28/2020.
* Linkedin : https://www.linkedin.com/in/josephsanjaya/
* Github : https://github.com/JosephSanjaya
*/

class CurrenciesRepository(private val service: APIInterface) : CurrenciesInterface {
    override suspend fun getCurrencies(
        base: String,
        symbols: String
    ): ApiResult<CurrenciesResponse> {
        return try {
            val result = service.getLatestCurrencyRatesAsync(base, symbols).await()
            ApiResult.Success(result)
        } catch (ex: Exception) {
            ApiResult.Error(ex)
        }
    }
    override suspend fun getRatesByDate(
        date: String,
        base: String,
        symbols: String
    ): ApiResult<CurrenciesResponse> {
        return try {
            val result = service.getHistoriesByDateAsync(date, base, symbols).await()
            ApiResult.Success(result)
        } catch (ex: Exception) {
            ApiResult.Error(ex)
        }
    }
    override suspend fun getRatesHistories(
        startDate: String,
        endDate: String,
        base: String,
        symbols: String
    ): ApiResult<CurrenciesHistoriesResponse> {
        return try {
            val result = service.getHistoriesCurrencyRatesAsync(startDate,endDate, base, symbols).await()
            ApiResult.Success(result)
        } catch (ex: Exception) {
            ApiResult.Error(ex)
        }
    }
}