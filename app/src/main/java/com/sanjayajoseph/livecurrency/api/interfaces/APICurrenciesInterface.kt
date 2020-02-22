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


interface APICurrenciesInterface {

    companion object {
        operator fun invoke(baseUrl: String, mContext: Context): APICurrenciesInterface {
            val baseService =
                APIService(
                    baseUrl,
                    mContext
                )
            return baseService.retrofit.create(APICurrenciesInterface::class.java)
        }
    }

    @GET("latest")
    fun getLatestCurrencyRates(
        @Query("base") type: String,
        @Query("symbols") service: String
    ): Observable<CurrenciesResponse>

    @GET("history")
    fun getHistoriesCurrencyRates(
        @Query("start_at") startAt: String,
        @Query("end_at") endAt: String,
        @Query("base") type: String,
        @Query("symbols") service: String
    ): Observable<ResponseBody>

    @GET("{date}")
    fun getHistoriesByDate(
        @Query("base") type: String,
        @Query("symbols") service: String,
        @Path("date") date: String
        ): Observable<CurrenciesResponse>

}