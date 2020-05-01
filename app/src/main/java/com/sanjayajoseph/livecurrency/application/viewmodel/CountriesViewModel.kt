package com.sanjayajoseph.livecurrency.application.viewmodel

import androidx.lifecycle.MutableLiveData
import com.sanjayajoseph.livecurrency.base.BaseViewModel
import com.sanjayajoseph.livecurrency.api.interfaces.ApiResult
import com.sanjayajoseph.livecurrency.api.models.countries.CountriesResponse
import com.sanjayajoseph.livecurrency.api.repository.CountriesRepository
import com.sanjayajoseph.livecurrency.application.common.Constants
import kotlinx.coroutines.launch
import timber.log.Timber


/*
* @author Joseph Sanjaya on 2/28/2020.
* Linkedin : https://www.linkedin.com/in/josephsanjaya/
* Github : https://github.com/JosephSanjaya
*/

class CountriesViewModel(private val countriesRepository: CountriesRepository) :
    BaseViewModel() {
    val countriesData: MutableLiveData<ArrayList<CountriesResponse>?> = MutableLiveData()
    val loadingCountries = MutableLiveData<Int>()

    fun getCountriesByAlpha(alpha: String) {
        loadingCountries.postValue(Constants.LOADING_STATUS_LOAD)
        ioScope.launch {
            val result = countriesRepository.getCountriesByAlpha(alpha)
            mainScope.launch {
                when (result) {
                    is ApiResult.Success -> {
                        countriesData.value = result.data
                        loadingCountries.postValue(Constants.LOADING_STATUS_SUCCESS)
                    }
                    is ApiResult.Error -> {
                        Timber.tag(Constants.TAG).e(result.exception.toString())
                        loadingCountries.postValue(Constants.LOADING_STATUS_FAILED)
                    }
                }
            }
        }
    }
}