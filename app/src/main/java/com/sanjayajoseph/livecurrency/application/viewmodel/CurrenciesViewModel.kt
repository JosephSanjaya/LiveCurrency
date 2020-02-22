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
    private val currenciesLiveData: LiveData<CurrenciesResponse?> = currenciesData

    fun getLatestCurrencyRates(base: String, symbols: String) {
        currenciesRepository.getLatestCurrencyRates(base, symbols,
            object : ApiCallBack<CurrenciesResponse> {
                override fun onError(error: Throwable) {
                    Timber.tag(Constants.TAG).e(error)
                    processError(error)
                }

                override fun onSucess(response: CurrenciesResponse) {
                    currenciesData.value = response
                }
            })
    }

    fun getLatestCurrencyRates(): LiveData<CurrenciesResponse?> {
        return currenciesLiveData
    }

}