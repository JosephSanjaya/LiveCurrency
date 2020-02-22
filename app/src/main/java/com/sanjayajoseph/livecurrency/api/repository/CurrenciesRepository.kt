package com.sanjayajoseph.livecurrency.api.repository

import com.sanjayajoseph.livecurrency.api.base.BaseRepository
import com.sanjayajoseph.livecurrency.api.interfaces.APICurrenciesInterface
import com.sanjayajoseph.livecurrency.api.interfaces.ApiCallBack
import com.sanjayajoseph.livecurrency.api.models.currencies.base.CurrenciesResponse
import io.reactivex.disposables.CompositeDisposable


/*
* @author Joseph Sanjaya on 2/22/2020.
* Linkedin : https://www.linkedin.com/in/josephsanjaya/
* Github : https://github.com/JosephSanjaya
*/

class CurrenciesRepository(service: APICurrenciesInterface, compositeDisposable: CompositeDisposable) :
    BaseRepository(service, compositeDisposable) {

    fun getLatestCurrencyRates(
        base: String,
        symbols: String,
        callback: ApiCallBack<CurrenciesResponse>
    ) {
        fetchData(service.getLatestCurrencyRates(base, symbols), callback)
    }
    fun getRateByDate(
        base: String,
        symbols: String,
        date: String,
        callback: ApiCallBack<CurrenciesResponse>
    ) {
        fetchData(service.getHistoriesByDate(base, symbols, date), callback)
    }
}