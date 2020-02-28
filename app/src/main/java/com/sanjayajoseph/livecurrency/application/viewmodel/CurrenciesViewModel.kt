package com.sanjayajoseph.livecurrency.application.viewmodel

import androidx.lifecycle.MutableLiveData
import com.sanjayajoseph.livecurrency.api.base.BaseViewModel
import com.sanjayajoseph.livecurrency.api.interfaces.ApiResult
import com.sanjayajoseph.livecurrency.api.models.currencies.base.CurrenciesResponse
import com.sanjayajoseph.livecurrency.api.repository.CurrenciesRepository
import com.sanjayajoseph.livecurrency.application.common.Constants
import kotlinx.coroutines.launch
import timber.log.Timber


/*
* @author Joseph Sanjaya on 2/28/2020.
* Linkedin : https://www.linkedin.com/in/josephsanjaya/
* Github : https://github.com/JosephSanjaya
*/

class CurrenciesViewModel(private val currenciesRepository: CurrenciesRepository) :
    BaseViewModel() {
    val currenciesData: MutableLiveData<CurrenciesResponse?> = MutableLiveData()
    val dateCurrenciesData: MutableLiveData<CurrenciesResponse?> = MutableLiveData()
    val loadingLatestCurrency = MutableLiveData<Int>()
    val loadingCurrencyByDate = MutableLiveData<Int>()

    fun getLatestCurrencyRates(base: String, symbols: String) {
        loadingLatestCurrency.postValue(Constants.LOADING_STATUS_LOAD)
        ioScope.launch {
            val result = currenciesRepository.getCurrencies(base, symbols)
            mainScope.launch {
                when (result) {
                    is ApiResult.Success -> {
                        currenciesData.value = result.data
                        loadingLatestCurrency.postValue(Constants.LOADING_STATUS_SUCCESS)
                    }
                    is ApiResult.Error -> {
                        Timber.tag(Constants.TAG).e(result.exception.toString())
                        loadingLatestCurrency.postValue(Constants.LOADING_STATUS_FAILED)
                    }
                }
            }
        }
    }

    fun getCurrencyRatesByDate(date: String, base: String, symbols: String) {
        loadingCurrencyByDate.postValue(Constants.LOADING_STATUS_LOAD)
        ioScope.launch {
            val result = currenciesRepository.getRatesByDate(date, base, symbols)
            mainScope.launch {
                when (result) {
                    is ApiResult.Success -> {
                        dateCurrenciesData.value = result.data
                        loadingCurrencyByDate.postValue(Constants.LOADING_STATUS_SUCCESS)
                    }
                    is ApiResult.Error -> {
                        Timber.tag(Constants.TAG).e(result.exception.toString())
                        loadingCurrencyByDate.postValue(Constants.LOADING_STATUS_FAILED)
                    }
                }
            }
        }
    }
}