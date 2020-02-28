package com.sanjayajoseph.livecurrency.application.common

import com.orhanobut.hawk.Hawk
import com.sanjayajoseph.livecurrency.api.models.countries.CountriesResponse
import com.sanjayajoseph.livecurrency.api.models.currencies.base.CurrenciesResponse
import timber.log.Timber


/*
* @author Joseph Sanjaya on 2/22/2020.
* Linkedin : https://www.linkedin.com/in/josephsanjaya/
* Github : https://github.com/JosephSanjaya
*/

object Hawk {
    fun setLatestCurrencyData(data: CurrenciesResponse) {
        Hawk.put("latestCurrencyData", data)
    }

    fun getLatestCurrencyData(): CurrenciesResponse {
        return Hawk.get("latestCurrencyData")
    }

    fun setSupportedCountry(data: ArrayList<CountriesResponse>) {
        Hawk.put("supportedCountries", data)
    }

    fun getSupportedCountry(): ArrayList<CountriesResponse>? {
        return try {
            Hawk.get("supportedCountries")
        }catch (e: Exception) {
            Timber.tag(Constants.TAG).e(e)
            null
        }
    }
}