package com.sanjayajoseph.livecurrency.application.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sanjayajoseph.livecurrency.api.interfaces.ApiCallBack
import com.sanjayajoseph.livecurrency.api.models.currencies.base.CurrenciesResponse
import com.sanjayajoseph.livecurrency.api.repository.CurrenciesRepository
import com.sanjayajoseph.livecurrency.application.base.BaseViewModel
import com.sanjayajoseph.livecurrency.application.common.Constants
import timber.log.Timber


/*
* @author Joseph Sanjaya on 2/22/2020.
* Linkedin : https://www.linkedin.com/in/josephsanjaya/
* Github : https://github.com/JosephSanjaya
*/

class CurrenciesViewModel(
    private val currenciesRepository: CurrenciesRepository
) : BaseViewModel<CurrenciesRepository>(currenciesRepository) {
    private var currenciesData: MutableLiveData<CurrenciesResponse?> = MutableLiveData()
    private var dateCurrenciesData: MutableLiveData<CurrenciesResponse?> = MutableLiveData()
    private val currenciesLiveData: LiveData<CurrenciesResponse?> = currenciesData
    private val dateCurrenciesLiveData: LiveData<CurrenciesResponse?> = dateCurrenciesData

    fun getLatestCurrencyRates(base: String, symbols: String,latestCurrencyAction: Runnable) {
        currenciesRepository.getLatestCurrencyRates(base, symbols,
            object : ApiCallBack<CurrenciesResponse> {
                override fun onError(error: Throwable) {
                    Timber.tag(Constants.TAG).e(error)
                    processError(error)
                    latestCurrencyAction.run()
                }

                override fun onSucess(response: CurrenciesResponse) {
                    currenciesData.value = response
                    latestCurrencyAction.run()
                }
            })
    }
    fun getCurrencyRatesByDate(date:String, base: String, symbols: String, yesterdayAction: Runnable) {
        currenciesRepository.getRateByDate(date, base, symbols,
            object : ApiCallBack<CurrenciesResponse> {
                override fun onError(error: Throwable) {
                    Timber.tag(Constants.TAG).e(error)
                    processError(error)
                    yesterdayAction.run()
                }

                override fun onSucess(response: CurrenciesResponse) {
                    dateCurrenciesData.value = response
                    yesterdayAction.run()
                }
            })
    }

    fun getCurrencyRates(): LiveData<CurrenciesResponse?> {
        return currenciesLiveData
    }
    fun getCurrencyRatesByDate(): LiveData<CurrenciesResponse?> {
        return dateCurrenciesLiveData
    }

}