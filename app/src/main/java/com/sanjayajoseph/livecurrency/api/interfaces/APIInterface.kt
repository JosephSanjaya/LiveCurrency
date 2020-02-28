package com.sanjayajoseph.livecurrency.api.interfaces

import android.content.Context
import com.sanjayajoseph.livecurrency.api.models.currencies.base.CurrenciesResponse
import com.sanjayajoseph.livecurrency.api.services.APIService
import com.squareup.okhttp.ResponseBody
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


/*
* @author Joseph Sanjaya on 2/22/2020.
* Linkedin : https://www.linkedin.com/in/josephsanjaya/
* Github : https://github.com/JosephSanjaya
*/


interface APIInterface {

    companion object {
        operator fun invoke(baseUrl: String, mContext: Context): APIInterface {
            val baseService =
                APIService(
                    baseUrl,
                    mContext
                )
            return baseService.retrofit.create(APIInterface::class.java)
        }
    }

    @GET("latest")
    fun getLatestCurrencyRatesAsync(
        @Query("base") type: String,
        @Query("symbols") service: String
    ): Deferred<CurrenciesResponse>

    @GET("history")
    fun getHistoriesCurrencyRatesAsync(
        @Query("start_at") startAt: String,
        @Query("end_at") endAt: String,
        @Query("base") type: String,
        @Query("symbols") service: String
    ): Deferred<ResponseBody>

    @GET("{date}")
    fun getHistoriesByDateAsync(
        @Path("date") date: String,
        @Query("base") type: String,
        @Query("symbols") service: String
        ): Deferred<CurrenciesResponse>

}