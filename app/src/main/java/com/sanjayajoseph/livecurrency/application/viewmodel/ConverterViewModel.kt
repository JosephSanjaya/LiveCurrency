package com.sanjayajoseph.livecurrency.application.viewmodel

import androidx.lifecycle.MutableLiveData
import com.sanjayajoseph.livecurrency.api.interfaces.ApiResult
import com.sanjayajoseph.livecurrency.api.repository.CurrenciesRepository
import com.sanjayajoseph.livecurrency.application.common.Constants
import com.sanjayajoseph.livecurrency.base.BaseViewModel
import kotlinx.coroutines.launch
import timber.log.Timber


/*
* @author Joseph Sanjaya on 3/1/2020.
* Linkedin : https://www.linkedin.com/in/josephsanjaya/
* Github : https://github.com/JosephSanjaya
*/

class ConverterViewModel(private val currenciesRepository: CurrenciesRepository) :
    BaseViewModel() {
    val convertedData: MutableLiveData<Double?> = MutableLiveData()
    val loadingConvert = MutableLiveData<Int>()

    fun convertRate(value: Double, base: String, symbols: String) {
        loadingConvert.postValue(Constants.LOADING_STATUS_LOAD)
        ioScope.launch {
            val result = currenciesRepository.getCurrencies(base, symbols)
            mainScope.launch {
                when (result) {
                    is ApiResult.Success -> {
                        when(symbols){
                            Constants.INDONESIA_CURRENCY -> convertedData.postValue(result.data.currenciesRates?.indonesiaCurrency?.times(value))
                            Constants.JAPAN_CURRENCY -> convertedData.postValue(result.data.currenciesRates?.japanCurrency?.times(value))
                            Constants.US_CURRENCY -> convertedData.postValue(result.data.currenciesRates?.usCurrency?.times(value))
                            Constants.CROATIAN_CURRENCY -> convertedData.postValue(result.data.currenciesRates?.croatianCurrency?.times(value))
                            Constants.AUSTRALIAN_CURRENCY -> convertedData.postValue(result.data.currenciesRates?.australianCurrency?.times(value))
                            Constants.BRAZILLIAN_CURRENCY -> convertedData.postValue(result.data.currenciesRates?.brazillianCurrency?.times(value))
                            Constants.BRITISH_CURRENCY -> convertedData.postValue(result.data.currenciesRates?.britishCurrency?.times(value))
                            Constants.BULGARIAN_CURRENCY -> convertedData.postValue(result.data.currenciesRates?.bulgarianCurrency?.times(value))
                            Constants.CANADIAN_CURRENCY -> convertedData.postValue(result.data.currenciesRates?.canadianCurrency?.times(value))
                            Constants.CHINESE_CURRENCY -> convertedData.postValue(result.data.currenciesRates?.chinaCurrency?.times(value))
                            Constants.CZECH_CURRENCY -> convertedData.postValue(result.data.currenciesRates?.czechCurrency?.times(value))
                            Constants.DANISH_CURRENCY -> convertedData.postValue(result.data.currenciesRates?.danishCurrency?.times(value))
                            Constants.EUROPEAN_CURRENCY -> convertedData.postValue(result.data.currenciesRates?.europeanCurrency?.times(value))
                            Constants.HONGKONG_CURRENCY -> convertedData.postValue(result.data.currenciesRates?.hongkongCurrency?.times(value))
                            Constants.HUNGARIAN_CURRENCY -> convertedData.postValue(result.data.currenciesRates?.hungarianCurrency?.times(value))
                            Constants.ICELANDIC_CURRENCY -> convertedData.postValue(result.data.currenciesRates?.icelandicCurrency?.times(value))
                            Constants.INDIA_CURRENCY -> convertedData.postValue(result.data.currenciesRates?.indianCurrecy?.times(value))
                            Constants.ISRAEL_CURRENCY -> convertedData.postValue(result.data.currenciesRates?.israelCurrency?.times(value))
                            Constants.MALAYSIAN_CURRENCY -> convertedData.postValue(result.data.currenciesRates?.malaysianCurrency?.times(value))
                            Constants.MEXICAN_CURRENCY -> convertedData.postValue(result.data.currenciesRates?.mexicanCurrency?.times(value))
                            Constants.NEW_ZEALAND_CURRENCY -> convertedData.postValue(result.data.currenciesRates?.newZealandCurrency?.times(value))
                            Constants.NORWEGIAN_CURRENCY -> convertedData.postValue(result.data.currenciesRates?.norwegianCurrency?.times(value))
                            Constants.PHILIPPINE_CURRENCY -> convertedData.postValue(result.data.currenciesRates?.philippineCurrency?.times(value))
                            Constants.POLAND_CURRENCY -> convertedData.postValue(result.data.currenciesRates?.polandCurrency?.times(value))
                            Constants.ROMANIAN_CURRENCY -> convertedData.postValue(result.data.currenciesRates?.romanianCurrency?.times(value))
                            Constants.RUSSIAN_CURRENCY -> convertedData.postValue(result.data.currenciesRates?.russianCurrency?.times(value))
                            Constants.SINGAPORE_CURRENCY -> convertedData.postValue(result.data.currenciesRates?.singaporeCurrency?.times(value))
                            Constants.SOUTH_AFRICAN_CURRENCY -> convertedData.postValue(result.data.currenciesRates?.southAfricanCurrency?.times(value))
                            Constants.SOUTH_KOREAN_CURRENCY -> convertedData.postValue(result.data.currenciesRates?.southKoreanCurrency?.times(value))
                            Constants.SWEDISH_CURRENCY -> convertedData.postValue(result.data.currenciesRates?.swedishCurrency?.times(value))
                            Constants.SWISH_CURRENCY -> convertedData.postValue(result.data.currenciesRates?.swishCurrency?.times(value))
                            Constants.THAI_CURRENCY -> convertedData.postValue(result.data.currenciesRates?.thaiCurrency?.times(value))
                            Constants.TURKISH_CURRENCY -> convertedData.postValue(result.data.currenciesRates?.turkishCurrency?.times(value))
                        }
                        loadingConvert.postValue(Constants.LOADING_STATUS_SUCCESS)
                    }
                    is ApiResult.Error -> {
                        Timber.tag(Constants.TAG).e(result.exception.toString())
                        loadingConvert.postValue(Constants.LOADING_STATUS_FAILED)
                    }
                }
            }
        }
    }

}