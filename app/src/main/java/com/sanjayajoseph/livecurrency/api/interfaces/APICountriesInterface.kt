package com.sanjayajoseph.livecurrency.api.interfaces

import android.content.Context
import com.sanjayajoseph.livecurrency.api.models.currencies.base.CurrenciesResponse
import com.sanjayajoseph.livecurrency.api.services.APIService
import com.squareup.okhttp.ResponseBody
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


/*
* @author Joseph Sanjaya on 2/22/2020.
* Linkedin : https://www.linkedin.com/in/josephsanjaya/
* Github : https://github.com/JosephSanjaya
*/


interface APICountriesInterface {

    companion object {
        operator fun invoke(baseUrl: String, mContext: Context): APICountriesInterface {
            val baseService =
                APIService(
                    baseUrl,
                    mContext
                )
            return baseService.retrofit.create(APICountriesInterface::class.java)
        }
    }
}