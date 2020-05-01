package com.sanjayajoseph.livecurrency.application.viewmodel

import androidx.lifecycle.MutableLiveData
import com.sanjayajoseph.livecurrency.api.interfaces.ApiResult
import com.sanjayajoseph.livecurrency.api.models.currencies.base.CurrenciesHistoriesResponse
import com.sanjayajoseph.livecurrency.api.repository.CurrenciesRepository
import com.sanjayajoseph.livecurrency.application.common.Constants
import com.sanjayajoseph.livecurrency.base.BaseViewModel
import kotlinx.coroutines.launch
import timber.log.Timber


/*
* @author Joseph Sanjaya on 2/28/2020.
* Linkedin : https://www.linkedin.com/in/josephsanjaya/
* Github : https://github.com/JosephSanjaya
*/

class HistoriesViewModel(private val currenciesRepository: CurrenciesRepository) :
    BaseViewModel() {
    val historiesData: MutableLiveData<CurrenciesHistoriesResponse?> = MutableLiveData()
    val loadingHistories = MutableLiveData<Int>()

    fun getHistories(startDate:String, endDate: String, base: String, symbols: String) {
        loadingHistories.postValue(Constants.LOADING_STATUS_LOAD)
        ioScope.launch {
            val result = currenciesRepository.getRatesHistories(startDate, endDate, base, symbols)
            mainScope.launch {
                when (result) {
                    is ApiResult.Success -> {
                        historiesData.value = result.data
                        loadingHistories.postValue(Constants.LOADING_STATUS_SUCCESS)
                    }
                    is ApiResult.Error -> {
                        Timber.tag(Constants.TAG).e(result.exception.toString())
                        loadingHistories.postValue(Constants.LOADING_STATUS_FAILED)
                    }
                }
            }
        }
    }
}