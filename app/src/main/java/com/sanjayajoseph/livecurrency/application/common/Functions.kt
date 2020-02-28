package com.sanjayajoseph.livecurrency.application.common

import android.view.View
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.blankj.utilcode.util.ToastUtils
import com.facebook.shimmer.ShimmerFrameLayout
import com.sanjayajoseph.livecurrency.api.models.countries.CountriesResponse
import com.sanjayajoseph.livecurrency.api.models.currencies.base.CurrenciesResponse
import com.sanjayajoseph.livecurrency.api.models.currencies.entities.CurrenciesData
import com.sanjayajoseph.livecurrency.api.models.currencies.entities.CurrenciesEntity
import timber.log.Timber
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


/*
* @author Joseph Sanjaya on 2/22/2020.
* Linkedin : https://www.linkedin.com/in/josephsanjaya/
* Github : https://github.com/JosephSanjaya
*/

object Functions {
    fun getCurrentDate(): String? {
        val dateFormat = SimpleDateFormat(Constants.API_DATE_FORMAT, Locale.getDefault())
        dateFormat.timeZone = TimeZone.getTimeZone("GMT")
        val c = Calendar.getInstance()
        c.add(
            Calendar.DATE,
            -2
        )
        return dateFormat.format(c.time)
    }

    fun getYesterdayDate(date: String): String {
        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val c = Calendar.getInstance()
        try {
            c.time = sdf.parse(date)!!
        } catch (e: ParseException) {
            Timber.tag(Constants.TAG).e(e.toString())
        }
        c.add(
            Calendar.DATE,
            -1
        )
        return sdf.format(c.time)
    }

    private fun calculateChanges(current: Double, yesterday: Double): Double {
        return ((current / yesterday) * 100) - 100
    }

    private fun bundleDataToEntities(
        type: String,
        currenciesData: CurrenciesResponse,
        yesterdayCurrenciesData: CurrenciesResponse,
        countryData: ArrayList<CountriesResponse>
    ): CurrenciesEntity {
        val tempEntity = CurrenciesEntity()
        val tempData = CurrenciesData()
        when (type) {
            Constants.CANADIAN_CURRENCY -> {
                if (currenciesData.currenciesRates?.canadianCurrency != null && yesterdayCurrenciesData.currenciesRates?.canadianCurrency != null) {
                    tempData.symbols = Constants.CANADIAN_CURRENCY
                    tempData.value = currenciesData.currenciesRates?.canadianCurrency
                    tempEntity.diffValue = calculateChanges(
                        currenciesData.currenciesRates?.canadianCurrency!!,
                        yesterdayCurrenciesData.currenciesRates?.canadianCurrency!!
                    )
                    if (currenciesData.currenciesRates?.canadianCurrency!! < yesterdayCurrenciesData.currenciesRates?.canadianCurrency!!)
                        tempEntity.type = Constants.HOME_CURRENCIES_TYPE_DOWN
                    else
                        tempEntity.type = Constants.HOME_CURRENCIES_TYPE_UP
                }
                tempEntity.data = tempData
                tempEntity.countryData =
                    countryData.first { data -> data.currencies?.any { currency -> currency?.code == Constants.CANADIAN_CURRENCY }!! }
            }
            Constants.HONGKONG_CURRENCY -> {
                if (currenciesData.currenciesRates?.hongkongCurrency != null && yesterdayCurrenciesData.currenciesRates?.hongkongCurrency != null) {
                    tempData.symbols = Constants.HONGKONG_CURRENCY
                    tempData.value = currenciesData.currenciesRates?.hongkongCurrency
                    tempEntity.diffValue = calculateChanges(
                        currenciesData.currenciesRates?.hongkongCurrency!!,
                        yesterdayCurrenciesData.currenciesRates?.hongkongCurrency!!
                    )
                    if (currenciesData.currenciesRates?.hongkongCurrency!! < yesterdayCurrenciesData.currenciesRates?.hongkongCurrency!!)
                        tempEntity.type = Constants.HOME_CURRENCIES_TYPE_DOWN
                    else
                        tempEntity.type = Constants.HOME_CURRENCIES_TYPE_UP
                }
                tempEntity.data = tempData
                tempEntity.countryData =
                    countryData.first { data -> data.currencies?.any { currency -> currency?.code == Constants.HONGKONG_CURRENCY }!! }
            }
            Constants.ICELANDIC_CURRENCY -> {
                if (currenciesData.currenciesRates?.icelandicCurrency != null && yesterdayCurrenciesData.currenciesRates?.icelandicCurrency != null) {
                    tempData.symbols = Constants.ICELANDIC_CURRENCY
                    tempData.value = currenciesData.currenciesRates?.icelandicCurrency
                    tempEntity.diffValue = calculateChanges(
                        currenciesData.currenciesRates?.icelandicCurrency!!,
                        yesterdayCurrenciesData.currenciesRates?.icelandicCurrency!!
                    )
                    if (currenciesData.currenciesRates?.icelandicCurrency!! < yesterdayCurrenciesData.currenciesRates?.icelandicCurrency!!)
                        tempEntity.type = Constants.HOME_CURRENCIES_TYPE_DOWN
                    else
                        tempEntity.type = Constants.HOME_CURRENCIES_TYPE_UP
                }
                tempEntity.data = tempData
                tempEntity.countryData =
                    countryData.first { data -> data.currencies?.any { currency -> currency?.code == Constants.ICELANDIC_CURRENCY }!! }
            }
            Constants.PHILIPPINE_CURRENCY -> {
                if (currenciesData.currenciesRates?.philippineCurrency != null && yesterdayCurrenciesData.currenciesRates?.philippineCurrency != null) {
                    tempData.symbols = Constants.PHILIPPINE_CURRENCY
                    tempData.value = currenciesData.currenciesRates?.philippineCurrency
                    tempEntity.diffValue = calculateChanges(
                        currenciesData.currenciesRates?.philippineCurrency!!,
                        yesterdayCurrenciesData.currenciesRates?.philippineCurrency!!
                    )
                    if (currenciesData.currenciesRates?.philippineCurrency!! < yesterdayCurrenciesData.currenciesRates?.philippineCurrency!!)
                        tempEntity.type = Constants.HOME_CURRENCIES_TYPE_DOWN
                    else
                        tempEntity.type = Constants.HOME_CURRENCIES_TYPE_UP
                }
                tempEntity.data = tempData
                tempEntity.countryData =
                    countryData.first { data -> data.currencies?.any { currency -> currency?.code == Constants.PHILIPPINE_CURRENCY }!! }
            }
            Constants.DANISH_CURRENCY -> {
                if (currenciesData.currenciesRates?.danishCurrency != null && yesterdayCurrenciesData.currenciesRates?.danishCurrency != null) {
                    tempData.symbols = Constants.DANISH_CURRENCY
                    tempData.value = currenciesData.currenciesRates?.danishCurrency
                    tempEntity.diffValue = calculateChanges(
                        currenciesData.currenciesRates?.danishCurrency!!,
                        yesterdayCurrenciesData.currenciesRates?.danishCurrency!!
                    )
                    if (currenciesData.currenciesRates?.danishCurrency!! < yesterdayCurrenciesData.currenciesRates?.danishCurrency!!)
                        tempEntity.type = Constants.HOME_CURRENCIES_TYPE_DOWN
                    else
                        tempEntity.type = Constants.HOME_CURRENCIES_TYPE_UP
                }
                tempEntity.data = tempData
                tempEntity.countryData =
                    countryData.first { data -> data.currencies?.any { currency -> currency?.code == Constants.DANISH_CURRENCY }!! }
            }
            Constants.HUNGARIAN_CURRENCY -> {
                if (currenciesData.currenciesRates?.hungarianCurrency != null && yesterdayCurrenciesData.currenciesRates?.hungarianCurrency != null) {
                    tempData.symbols = Constants.HUNGARIAN_CURRENCY
                    tempData.value = currenciesData.currenciesRates?.hungarianCurrency
                    tempEntity.diffValue = calculateChanges(
                        currenciesData.currenciesRates?.hungarianCurrency!!,
                        yesterdayCurrenciesData.currenciesRates?.hungarianCurrency!!
                    )
                    if (currenciesData.currenciesRates?.hungarianCurrency!! < yesterdayCurrenciesData.currenciesRates?.hungarianCurrency!!)
                        tempEntity.type = Constants.HOME_CURRENCIES_TYPE_DOWN
                    else
                        tempEntity.type = Constants.HOME_CURRENCIES_TYPE_UP
                }
                tempEntity.data = tempData
                tempEntity.countryData =
                    countryData.first { data -> data.currencies?.any { currency -> currency?.code == Constants.HUNGARIAN_CURRENCY }!! }
            }
            Constants.CZECH_CURRENCY -> {
                if (currenciesData.currenciesRates?.czechCurrency != null && yesterdayCurrenciesData.currenciesRates?.czechCurrency != null) {
                    tempData.symbols = Constants.CZECH_CURRENCY
                    tempData.value = currenciesData.currenciesRates?.czechCurrency
                    tempEntity.diffValue = calculateChanges(
                        currenciesData.currenciesRates?.czechCurrency!!,
                        yesterdayCurrenciesData.currenciesRates?.czechCurrency!!
                    )
                    if (currenciesData.currenciesRates?.czechCurrency!! < yesterdayCurrenciesData.currenciesRates?.czechCurrency!!)
                        tempEntity.type = Constants.HOME_CURRENCIES_TYPE_DOWN
                    else
                        tempEntity.type = Constants.HOME_CURRENCIES_TYPE_UP
                }
                tempEntity.data = tempData
                tempEntity.countryData =
                    countryData.first { data -> data.currencies?.any { currency -> currency?.code == Constants.CZECH_CURRENCY }!! }
            }
            Constants.BRITISH_CURRENCY -> {
                if (currenciesData.currenciesRates?.britishCurrency != null && yesterdayCurrenciesData.currenciesRates?.britishCurrency != null) {
                    tempData.symbols = Constants.BRITISH_CURRENCY
                    tempData.value = currenciesData.currenciesRates?.britishCurrency
                    tempEntity.diffValue = calculateChanges(
                        currenciesData.currenciesRates?.britishCurrency!!,
                        yesterdayCurrenciesData.currenciesRates?.britishCurrency!!
                    )
                    if (currenciesData.currenciesRates?.britishCurrency!! < yesterdayCurrenciesData.currenciesRates?.britishCurrency!!)
                        tempEntity.type = Constants.HOME_CURRENCIES_TYPE_DOWN
                    else
                        tempEntity.type = Constants.HOME_CURRENCIES_TYPE_UP
                }
                tempEntity.data = tempData
                tempEntity.countryData =
                    countryData.first { data -> data.currencies?.any { currency -> currency?.code == Constants.BRITISH_CURRENCY }!! }
            }
            Constants.ROMANIAN_CURRENCY -> {
                if (currenciesData.currenciesRates?.romanianCurrency != null && yesterdayCurrenciesData.currenciesRates?.romanianCurrency != null) {
                    tempData.symbols = Constants.ROMANIAN_CURRENCY
                    tempData.value = currenciesData.currenciesRates?.romanianCurrency
                    tempEntity.diffValue = calculateChanges(
                        currenciesData.currenciesRates?.romanianCurrency!!,
                        yesterdayCurrenciesData.currenciesRates?.romanianCurrency!!
                    )
                    if (currenciesData.currenciesRates?.romanianCurrency!! < yesterdayCurrenciesData.currenciesRates?.romanianCurrency!!)
                        tempEntity.type = Constants.HOME_CURRENCIES_TYPE_DOWN
                    else
                        tempEntity.type = Constants.HOME_CURRENCIES_TYPE_UP
                }
                tempEntity.data = tempData
                tempEntity.countryData =
                    countryData.first { data -> data.currencies?.any { currency -> currency?.code == Constants.ROMANIAN_CURRENCY }!! }
            }
            Constants.SWEDISH_CURRENCY -> {
                if (currenciesData.currenciesRates?.swedishCurrency != null && yesterdayCurrenciesData.currenciesRates?.swedishCurrency != null) {
                    tempData.symbols = Constants.SWEDISH_CURRENCY
                    tempData.value = currenciesData.currenciesRates?.swedishCurrency
                    tempEntity.diffValue = calculateChanges(
                        currenciesData.currenciesRates?.swedishCurrency!!,
                        yesterdayCurrenciesData.currenciesRates?.swedishCurrency!!
                    )
                    if (currenciesData.currenciesRates?.swedishCurrency!! < yesterdayCurrenciesData.currenciesRates?.swedishCurrency!!)
                        tempEntity.type = Constants.HOME_CURRENCIES_TYPE_DOWN
                    else
                        tempEntity.type = Constants.HOME_CURRENCIES_TYPE_UP
                }
                tempEntity.data = tempData
                tempEntity.countryData =
                    countryData.first { data -> data.currencies?.any { currency -> currency?.code == Constants.SWEDISH_CURRENCY }!! }
            }
            Constants.INDONESIA_CURRENCY -> {
                if (currenciesData.currenciesRates?.indonesiaCurrency != null && yesterdayCurrenciesData.currenciesRates?.indonesiaCurrency != null) {
                    tempData.symbols = Constants.INDONESIA_CURRENCY
                    tempData.value = currenciesData.currenciesRates?.indonesiaCurrency
                    tempEntity.diffValue = calculateChanges(
                        currenciesData.currenciesRates?.indonesiaCurrency!!,
                        yesterdayCurrenciesData.currenciesRates?.indonesiaCurrency!!
                    )
                    if (currenciesData.currenciesRates?.indonesiaCurrency!! < yesterdayCurrenciesData.currenciesRates?.indonesiaCurrency!!)
                        tempEntity.type = Constants.HOME_CURRENCIES_TYPE_DOWN
                    else
                        tempEntity.type = Constants.HOME_CURRENCIES_TYPE_UP
                }
                tempEntity.data = tempData
                tempEntity.countryData =
                    countryData.first { data -> data.currencies?.any { currency -> currency?.code == Constants.INDONESIA_CURRENCY }!! }
            }
            Constants.INDIA_CURRENCY -> {
                if (currenciesData.currenciesRates?.indianCurrecy != null && yesterdayCurrenciesData.currenciesRates?.indianCurrecy != null) {
                    tempData.symbols = Constants.INDIA_CURRENCY
                    tempData.value = currenciesData.currenciesRates?.indianCurrecy
                    tempEntity.diffValue = calculateChanges(
                        currenciesData.currenciesRates?.indianCurrecy!!,
                        yesterdayCurrenciesData.currenciesRates?.indianCurrecy!!
                    )
                    if (currenciesData.currenciesRates?.indianCurrecy!! < yesterdayCurrenciesData.currenciesRates?.indianCurrecy!!)
                        tempEntity.type = Constants.HOME_CURRENCIES_TYPE_DOWN
                    else
                        tempEntity.type = Constants.HOME_CURRENCIES_TYPE_UP
                }
                tempEntity.data = tempData
                tempEntity.countryData =
                    countryData.first { data -> data.currencies?.any { currency -> currency?.code == Constants.INDIA_CURRENCY }!! }
            }
            Constants.BRAZILLIAN_CURRENCY -> {
                if (currenciesData.currenciesRates?.brazillianCurrency != null && yesterdayCurrenciesData.currenciesRates?.brazillianCurrency != null) {
                    tempData.symbols = Constants.BRAZILLIAN_CURRENCY
                    tempData.value = currenciesData.currenciesRates?.brazillianCurrency
                    tempEntity.diffValue = calculateChanges(
                        currenciesData.currenciesRates?.brazillianCurrency!!,
                        yesterdayCurrenciesData.currenciesRates?.brazillianCurrency!!
                    )
                    if (currenciesData.currenciesRates?.brazillianCurrency!! < yesterdayCurrenciesData.currenciesRates?.brazillianCurrency!!)
                        tempEntity.type = Constants.HOME_CURRENCIES_TYPE_DOWN
                    else
                        tempEntity.type = Constants.HOME_CURRENCIES_TYPE_UP
                }
                tempEntity.data = tempData
                tempEntity.countryData =
                    countryData.first { data -> data.currencies?.any { currency -> currency?.code == Constants.BRAZILLIAN_CURRENCY }!! }
            }
            Constants.RUSSIAN_CURRENCY -> {
                if (currenciesData.currenciesRates?.russianCurrency != null && yesterdayCurrenciesData.currenciesRates?.russianCurrency != null) {
                    tempData.symbols = Constants.RUSSIAN_CURRENCY
                    tempData.value = currenciesData.currenciesRates?.russianCurrency
                    tempEntity.diffValue = calculateChanges(
                        currenciesData.currenciesRates?.russianCurrency!!,
                        yesterdayCurrenciesData.currenciesRates?.russianCurrency!!
                    )
                    if (currenciesData.currenciesRates?.russianCurrency!! < yesterdayCurrenciesData.currenciesRates?.russianCurrency!!)
                        tempEntity.type = Constants.HOME_CURRENCIES_TYPE_DOWN
                    else
                        tempEntity.type = Constants.HOME_CURRENCIES_TYPE_UP
                }
                tempEntity.data = tempData
                tempEntity.countryData =
                    countryData.first { data -> data.currencies?.any { currency -> currency?.code == Constants.RUSSIAN_CURRENCY }!! }
            }
            Constants.CROATIAN_CURRENCY -> {
                if (currenciesData.currenciesRates?.croatianCurrency != null && yesterdayCurrenciesData.currenciesRates?.croatianCurrency != null) {
                    tempData.symbols = Constants.CROATIAN_CURRENCY
                    tempData.value = currenciesData.currenciesRates?.croatianCurrency
                    tempEntity.diffValue = calculateChanges(
                        currenciesData.currenciesRates?.croatianCurrency!!,
                        yesterdayCurrenciesData.currenciesRates?.croatianCurrency!!
                    )
                    if (currenciesData.currenciesRates?.croatianCurrency!! < yesterdayCurrenciesData.currenciesRates?.croatianCurrency!!)
                        tempEntity.type = Constants.HOME_CURRENCIES_TYPE_DOWN
                    else
                        tempEntity.type = Constants.HOME_CURRENCIES_TYPE_UP
                }
                tempEntity.data = tempData
                tempEntity.countryData =
                    countryData.first { data -> data.currencies?.any { currency -> currency?.code == Constants.CROATIAN_CURRENCY }!! }
            }
            Constants.JAPAN_CURRENCY -> {
                if (currenciesData.currenciesRates?.japanCurrency != null && yesterdayCurrenciesData.currenciesRates?.japanCurrency != null) {
                    tempData.symbols = Constants.JAPAN_CURRENCY
                    tempData.value = currenciesData.currenciesRates?.japanCurrency
                    tempEntity.diffValue = calculateChanges(
                        currenciesData.currenciesRates?.japanCurrency!!,
                        yesterdayCurrenciesData.currenciesRates?.japanCurrency!!
                    )
                    if (currenciesData.currenciesRates?.japanCurrency!! < yesterdayCurrenciesData.currenciesRates?.japanCurrency!!)
                        tempEntity.type = Constants.HOME_CURRENCIES_TYPE_DOWN
                    else
                        tempEntity.type = Constants.HOME_CURRENCIES_TYPE_UP
                }
                tempEntity.data = tempData
                tempEntity.countryData =
                    countryData.first { data -> data.currencies?.any { currency -> currency?.code == Constants.JAPAN_CURRENCY }!! }
            }
            Constants.THAI_CURRENCY -> {
                if (currenciesData.currenciesRates?.thaiCurrency != null && yesterdayCurrenciesData.currenciesRates?.thaiCurrency != null) {
                    tempData.symbols = Constants.THAI_CURRENCY
                    tempData.value = currenciesData.currenciesRates?.thaiCurrency
                    tempEntity.diffValue = calculateChanges(
                        currenciesData.currenciesRates?.thaiCurrency!!,
                        yesterdayCurrenciesData.currenciesRates?.thaiCurrency!!
                    )
                    if (currenciesData.currenciesRates?.thaiCurrency!! < yesterdayCurrenciesData.currenciesRates?.thaiCurrency!!)
                        tempEntity.type = Constants.HOME_CURRENCIES_TYPE_DOWN
                    else
                        tempEntity.type = Constants.HOME_CURRENCIES_TYPE_UP
                }
                tempEntity.data = tempData
                tempEntity.countryData =
                    countryData.first { data -> data.currencies?.any { currency -> currency?.code == Constants.THAI_CURRENCY }!! }
            }
            Constants.SWISH_CURRENCY -> {
                if (currenciesData.currenciesRates?.swishCurrency != null && yesterdayCurrenciesData.currenciesRates?.swishCurrency != null) {
                    tempData.symbols = Constants.SWISH_CURRENCY
                    tempData.value = currenciesData.currenciesRates?.swishCurrency
                    tempEntity.diffValue = calculateChanges(
                        currenciesData.currenciesRates?.swishCurrency!!,
                        yesterdayCurrenciesData.currenciesRates?.swishCurrency!!
                    )
                    if (currenciesData.currenciesRates?.swishCurrency!! < yesterdayCurrenciesData.currenciesRates?.swishCurrency!!)
                        tempEntity.type = Constants.HOME_CURRENCIES_TYPE_DOWN
                    else
                        tempEntity.type = Constants.HOME_CURRENCIES_TYPE_UP
                }
                tempEntity.data = tempData
                tempEntity.countryData =
                    countryData.first { data -> data.currencies?.any { currency -> currency?.code == Constants.SWISH_CURRENCY }!! }
            }
            Constants.EUROPEAN_CURRENCY -> {
                if (currenciesData.currenciesRates?.europeanCurrency != null && yesterdayCurrenciesData.currenciesRates?.europeanCurrency != null) {
                    tempData.symbols = Constants.EUROPEAN_CURRENCY
                    tempData.value = currenciesData.currenciesRates?.europeanCurrency
                    tempEntity.diffValue = calculateChanges(
                        currenciesData.currenciesRates?.europeanCurrency!!,
                        yesterdayCurrenciesData.currenciesRates?.europeanCurrency!!
                    )
                    if (currenciesData.currenciesRates?.europeanCurrency!! < yesterdayCurrenciesData.currenciesRates?.europeanCurrency!!)
                        tempEntity.type = Constants.HOME_CURRENCIES_TYPE_DOWN
                    else
                        tempEntity.type = Constants.HOME_CURRENCIES_TYPE_UP
                }
                tempEntity.data = tempData
                tempEntity.countryData =
                    countryData.first { data -> data.currencies?.any { currency -> currency?.code == Constants.EUROPEAN_CURRENCY }!! }
            }
            Constants.MALAYSIAN_CURRENCY -> {
                if (currenciesData.currenciesRates?.malaysianCurrency != null && yesterdayCurrenciesData.currenciesRates?.malaysianCurrency != null) {
                    tempData.symbols = Constants.MALAYSIAN_CURRENCY
                    tempData.value = currenciesData.currenciesRates?.malaysianCurrency
                    tempEntity.diffValue = calculateChanges(
                        currenciesData.currenciesRates?.malaysianCurrency!!,
                        yesterdayCurrenciesData.currenciesRates?.malaysianCurrency!!
                    )
                    if (currenciesData.currenciesRates?.malaysianCurrency!! < yesterdayCurrenciesData.currenciesRates?.malaysianCurrency!!)
                        tempEntity.type = Constants.HOME_CURRENCIES_TYPE_DOWN
                    else
                        tempEntity.type = Constants.HOME_CURRENCIES_TYPE_UP
                }
                tempEntity.data = tempData
                tempEntity.countryData =
                    countryData.first { data -> data.currencies?.any { currency -> currency?.code == Constants.MALAYSIAN_CURRENCY }!! }
            }
            Constants.BULGARIAN_CURRENCY -> {
                if (currenciesData.currenciesRates?.bulgarianCurrency != null && yesterdayCurrenciesData.currenciesRates?.bulgarianCurrency != null) {
                    tempData.symbols = Constants.BULGARIAN_CURRENCY
                    tempData.value = currenciesData.currenciesRates?.bulgarianCurrency
                    tempEntity.diffValue = calculateChanges(
                        currenciesData.currenciesRates?.bulgarianCurrency!!,
                        yesterdayCurrenciesData.currenciesRates?.bulgarianCurrency!!
                    )
                    if (currenciesData.currenciesRates?.bulgarianCurrency!! < yesterdayCurrenciesData.currenciesRates?.bulgarianCurrency!!)
                        tempEntity.type = Constants.HOME_CURRENCIES_TYPE_DOWN
                    else
                        tempEntity.type = Constants.HOME_CURRENCIES_TYPE_UP
                }
                tempEntity.data = tempData
                tempEntity.countryData =
                    countryData.first { data -> data.currencies?.any { currency -> currency?.code == Constants.BULGARIAN_CURRENCY }!! }
            }
            Constants.TURKISH_CURRENCY -> {
                if (currenciesData.currenciesRates?.turkishCurrency != null && yesterdayCurrenciesData.currenciesRates?.turkishCurrency != null) {
                    tempData.symbols = Constants.TURKISH_CURRENCY
                    tempData.value = currenciesData.currenciesRates?.turkishCurrency
                    tempEntity.diffValue = calculateChanges(
                        currenciesData.currenciesRates?.turkishCurrency!!,
                        yesterdayCurrenciesData.currenciesRates?.turkishCurrency!!
                    )
                    if (currenciesData.currenciesRates?.turkishCurrency!! < yesterdayCurrenciesData.currenciesRates?.turkishCurrency!!)
                        tempEntity.type = Constants.HOME_CURRENCIES_TYPE_DOWN
                    else
                        tempEntity.type = Constants.HOME_CURRENCIES_TYPE_UP
                }
                tempEntity.data = tempData
                tempEntity.countryData =
                    countryData.first { data -> data.currencies?.any { currency -> currency?.code == Constants.TURKISH_CURRENCY }!! }
            }
            Constants.CHINESE_CURRENCY -> {
                if (currenciesData.currenciesRates?.chinaCurrency != null && yesterdayCurrenciesData.currenciesRates?.chinaCurrency != null) {
                    tempData.symbols = Constants.CHINESE_CURRENCY
                    tempData.value = currenciesData.currenciesRates?.chinaCurrency
                    tempEntity.diffValue = calculateChanges(
                        currenciesData.currenciesRates?.chinaCurrency!!,
                        yesterdayCurrenciesData.currenciesRates?.chinaCurrency!!
                    )
                    if (currenciesData.currenciesRates?.chinaCurrency!! < yesterdayCurrenciesData.currenciesRates?.chinaCurrency!!)
                        tempEntity.type = Constants.HOME_CURRENCIES_TYPE_DOWN
                    else
                        tempEntity.type = Constants.HOME_CURRENCIES_TYPE_UP
                }
                tempEntity.data = tempData
                tempEntity.countryData =
                    countryData.first { data -> data.currencies?.any { currency -> currency?.code == Constants.CHINESE_CURRENCY }!! }
            }
            Constants.NORWEGIAN_CURRENCY -> {
                if (currenciesData.currenciesRates?.norwegianCurrency != null && yesterdayCurrenciesData.currenciesRates?.norwegianCurrency != null) {
                    tempData.symbols = Constants.NORWEGIAN_CURRENCY
                    tempData.value = currenciesData.currenciesRates?.norwegianCurrency
                    tempEntity.diffValue = calculateChanges(
                        currenciesData.currenciesRates?.norwegianCurrency!!,
                        yesterdayCurrenciesData.currenciesRates?.norwegianCurrency!!
                    )
                    if (currenciesData.currenciesRates?.norwegianCurrency!! < yesterdayCurrenciesData.currenciesRates?.norwegianCurrency!!)
                        tempEntity.type = Constants.HOME_CURRENCIES_TYPE_DOWN
                    else
                        tempEntity.type = Constants.HOME_CURRENCIES_TYPE_UP
                }
                tempEntity.data = tempData
                tempEntity.countryData =
                    countryData.first { data -> data.currencies?.any { currency -> currency?.code == Constants.NORWEGIAN_CURRENCY }!! }
            }
            Constants.NEW_ZEALAND_CURRENCY -> {
                if (currenciesData.currenciesRates?.newZealandCurrency != null && yesterdayCurrenciesData.currenciesRates?.newZealandCurrency != null) {
                    tempData.symbols = Constants.NEW_ZEALAND_CURRENCY
                    tempData.value = currenciesData.currenciesRates?.newZealandCurrency
                    tempEntity.diffValue = calculateChanges(
                        currenciesData.currenciesRates?.newZealandCurrency!!,
                        yesterdayCurrenciesData.currenciesRates?.newZealandCurrency!!
                    )
                    if (currenciesData.currenciesRates?.newZealandCurrency!! < yesterdayCurrenciesData.currenciesRates?.newZealandCurrency!!)
                        tempEntity.type = Constants.HOME_CURRENCIES_TYPE_DOWN
                    else
                        tempEntity.type = Constants.HOME_CURRENCIES_TYPE_UP
                }
                tempEntity.data = tempData
                tempEntity.countryData =
                    countryData.first { data -> data.currencies?.any { currency -> currency?.code == Constants.NEW_ZEALAND_CURRENCY }!! }
            }
            Constants.SOUTH_AFRICAN_CURRENCY -> {
                if (currenciesData.currenciesRates?.southAfricanCurrency != null && yesterdayCurrenciesData.currenciesRates?.southAfricanCurrency != null) {
                    tempData.symbols = Constants.SOUTH_AFRICAN_CURRENCY
                    tempData.value = currenciesData.currenciesRates?.southAfricanCurrency
                    tempEntity.diffValue = calculateChanges(
                        currenciesData.currenciesRates?.southAfricanCurrency!!,
                        yesterdayCurrenciesData.currenciesRates?.southAfricanCurrency!!
                    )
                    if (currenciesData.currenciesRates?.southAfricanCurrency!! < yesterdayCurrenciesData.currenciesRates?.southAfricanCurrency!!)
                        tempEntity.type = Constants.HOME_CURRENCIES_TYPE_DOWN
                    else
                        tempEntity.type = Constants.HOME_CURRENCIES_TYPE_UP
                }
                tempEntity.data = tempData
                tempEntity.countryData =
                    countryData.first { data -> data.currencies?.any { currency -> currency?.code == Constants.SOUTH_AFRICAN_CURRENCY }!! }
            }
            Constants.US_CURRENCY -> {
                if (currenciesData.currenciesRates?.usCurrency != null && yesterdayCurrenciesData.currenciesRates?.usCurrency != null) {
                    tempData.symbols = Constants.US_CURRENCY
                    tempData.value = currenciesData.currenciesRates?.usCurrency
                    tempEntity.diffValue = calculateChanges(
                        currenciesData.currenciesRates?.usCurrency!!,
                        yesterdayCurrenciesData.currenciesRates?.usCurrency!!
                    )
                    if (currenciesData.currenciesRates?.usCurrency!! < yesterdayCurrenciesData.currenciesRates?.usCurrency!!)
                        tempEntity.type = Constants.HOME_CURRENCIES_TYPE_DOWN
                    else
                        tempEntity.type = Constants.HOME_CURRENCIES_TYPE_UP
                }
                tempEntity.data = tempData
                tempEntity.countryData =
                    countryData.first { data -> data.currencies?.any { currency -> currency?.code == Constants.US_CURRENCY }!! }
            }
            Constants.MEXICAN_CURRENCY -> {
                if (currenciesData.currenciesRates?.mexicanCurrency != null && yesterdayCurrenciesData.currenciesRates?.mexicanCurrency != null) {
                    tempData.symbols = Constants.MEXICAN_CURRENCY
                    tempData.value = currenciesData.currenciesRates?.mexicanCurrency
                    tempEntity.diffValue = calculateChanges(
                        currenciesData.currenciesRates?.mexicanCurrency!!,
                        yesterdayCurrenciesData.currenciesRates?.mexicanCurrency!!
                    )
                    if (currenciesData.currenciesRates?.mexicanCurrency!! < yesterdayCurrenciesData.currenciesRates?.mexicanCurrency!!)
                        tempEntity.type = Constants.HOME_CURRENCIES_TYPE_DOWN
                    else
                        tempEntity.type = Constants.HOME_CURRENCIES_TYPE_UP
                }
                tempEntity.data = tempData
                tempEntity.countryData =
                    countryData.first { data -> data.currencies?.any { currency -> currency?.code == Constants.MEXICAN_CURRENCY }!! }
            }
            Constants.SINGAPORE_CURRENCY -> {
                if (currenciesData.currenciesRates?.singaporeCurrency != null && yesterdayCurrenciesData.currenciesRates?.singaporeCurrency != null) {
                    tempData.symbols = Constants.SINGAPORE_CURRENCY
                    tempData.value = currenciesData.currenciesRates?.singaporeCurrency
                    tempEntity.diffValue = calculateChanges(
                        currenciesData.currenciesRates?.singaporeCurrency!!,
                        yesterdayCurrenciesData.currenciesRates?.singaporeCurrency!!
                    )
                    if (currenciesData.currenciesRates?.singaporeCurrency!! < yesterdayCurrenciesData.currenciesRates?.singaporeCurrency!!)
                        tempEntity.type = Constants.HOME_CURRENCIES_TYPE_DOWN
                    else
                        tempEntity.type = Constants.HOME_CURRENCIES_TYPE_UP
                }
                tempEntity.data = tempData
                tempEntity.countryData =
                    countryData.first { data -> data.currencies?.any { currency -> currency?.code == Constants.SINGAPORE_CURRENCY }!! }
            }
            Constants.AUSTRALIAN_CURRENCY -> {
                if (currenciesData.currenciesRates?.australianCurrency != null && yesterdayCurrenciesData.currenciesRates?.australianCurrency != null) {
                    tempData.symbols = Constants.AUSTRALIAN_CURRENCY
                    tempData.value = currenciesData.currenciesRates?.australianCurrency
                    tempEntity.diffValue = calculateChanges(
                        currenciesData.currenciesRates?.australianCurrency!!,
                        yesterdayCurrenciesData.currenciesRates?.australianCurrency!!
                    )
                    if (currenciesData.currenciesRates?.australianCurrency!! < yesterdayCurrenciesData.currenciesRates?.australianCurrency!!)
                        tempEntity.type = Constants.HOME_CURRENCIES_TYPE_DOWN
                    else
                        tempEntity.type = Constants.HOME_CURRENCIES_TYPE_UP
                }
                tempEntity.data = tempData
                tempEntity.countryData =
                    countryData.first { data -> data.currencies?.any { currency -> currency?.code == Constants.AUSTRALIAN_CURRENCY }!! }
            }
            Constants.ISRAEL_CURRENCY -> {
                if (currenciesData.currenciesRates?.israelCurrency != null && yesterdayCurrenciesData.currenciesRates?.israelCurrency != null) {
                    tempData.symbols = Constants.ISRAEL_CURRENCY
                    tempData.value = currenciesData.currenciesRates?.israelCurrency
                    tempEntity.diffValue = calculateChanges(
                        currenciesData.currenciesRates?.israelCurrency!!,
                        yesterdayCurrenciesData.currenciesRates?.israelCurrency!!
                    )
                    if (currenciesData.currenciesRates?.israelCurrency!! < yesterdayCurrenciesData.currenciesRates?.israelCurrency!!)
                        tempEntity.type = Constants.HOME_CURRENCIES_TYPE_DOWN
                    else
                        tempEntity.type = Constants.HOME_CURRENCIES_TYPE_UP
                }
                tempEntity.data = tempData
                tempEntity.countryData =
                    countryData.first { data -> data.currencies?.any { currency -> currency?.code == Constants.ISRAEL_CURRENCY }!! }
            }
            Constants.SOUTH_KOREAN_CURRENCY -> {
                if (currenciesData.currenciesRates?.southKoreanCurrency != null && yesterdayCurrenciesData.currenciesRates?.southKoreanCurrency != null) {
                    tempData.symbols = Constants.SOUTH_KOREAN_CURRENCY
                    tempData.value = currenciesData.currenciesRates?.southKoreanCurrency
                    tempEntity.diffValue = calculateChanges(
                        currenciesData.currenciesRates?.southKoreanCurrency!!,
                        yesterdayCurrenciesData.currenciesRates?.southKoreanCurrency!!
                    )
                    if (currenciesData.currenciesRates?.southKoreanCurrency!! < yesterdayCurrenciesData.currenciesRates?.southKoreanCurrency!!)
                        tempEntity.type = Constants.HOME_CURRENCIES_TYPE_DOWN
                    else
                        tempEntity.type = Constants.HOME_CURRENCIES_TYPE_UP
                }
                tempEntity.data = tempData
                tempEntity.countryData =
                    countryData.first { data -> data.currencies?.any { currency -> currency?.code == Constants.SOUTH_KOREAN_CURRENCY }!! }
            }
            Constants.POLAND_CURRENCY -> {
                if (currenciesData.currenciesRates?.polandCurrency != null && yesterdayCurrenciesData.currenciesRates?.polandCurrency != null) {
                    tempData.symbols = Constants.POLAND_CURRENCY
                    tempData.value = currenciesData.currenciesRates?.polandCurrency
                    tempEntity.diffValue = calculateChanges(
                        currenciesData.currenciesRates?.polandCurrency!!,
                        yesterdayCurrenciesData.currenciesRates?.polandCurrency!!
                    )
                    if (currenciesData.currenciesRates?.polandCurrency!! < yesterdayCurrenciesData.currenciesRates?.polandCurrency!!)
                        tempEntity.type = Constants.HOME_CURRENCIES_TYPE_DOWN
                    else
                        tempEntity.type = Constants.HOME_CURRENCIES_TYPE_UP
                }
                tempEntity.data = tempData
                tempEntity.countryData =
                    countryData.first { data -> data.currencies?.any { currency -> currency?.code == Constants.POLAND_CURRENCY }!! }
            }
        }
        return tempEntity
    }

    fun bundleDataToArray(
        currenciesData: CurrenciesResponse,
        yesterdayCurrenciesData: CurrenciesResponse,
        countryData: ArrayList<CountriesResponse>
    ): ArrayList<CurrenciesEntity> {
        val currenciesDataList: ArrayList<CurrenciesEntity> = ArrayList()
        try {
            currenciesDataList.add(
                bundleDataToEntities(
                    Constants.CANADIAN_CURRENCY,
                    currenciesData,
                    yesterdayCurrenciesData,
                    countryData
                )
            )
            currenciesDataList.add(
                bundleDataToEntities(
                    Constants.HONGKONG_CURRENCY,
                    currenciesData,
                    yesterdayCurrenciesData,
                    countryData
                )
            )
            currenciesDataList.add(
                bundleDataToEntities(
                    Constants.ICELANDIC_CURRENCY,
                    currenciesData,
                    yesterdayCurrenciesData,
                    countryData
                )
            )
            currenciesDataList.add(
                bundleDataToEntities(
                    Constants.PHILIPPINE_CURRENCY,
                    currenciesData,
                    yesterdayCurrenciesData,
                    countryData
                )
            )
            currenciesDataList.add(
                bundleDataToEntities(
                    Constants.DANISH_CURRENCY,
                    currenciesData,
                    yesterdayCurrenciesData,
                    countryData
                )
            )
            currenciesDataList.add(
                bundleDataToEntities(
                    Constants.HUNGARIAN_CURRENCY,
                    currenciesData,
                    yesterdayCurrenciesData,
                    countryData
                )
            )
            currenciesDataList.add(
                bundleDataToEntities(
                    Constants.CZECH_CURRENCY,
                    currenciesData,
                    yesterdayCurrenciesData,
                    countryData
                )
            )
            currenciesDataList.add(
                bundleDataToEntities(
                    Constants.BRITISH_CURRENCY,
                    currenciesData,
                    yesterdayCurrenciesData,
                    countryData
                )
            )
            currenciesDataList.add(
                bundleDataToEntities(
                    Constants.ROMANIAN_CURRENCY,
                    currenciesData,
                    yesterdayCurrenciesData,
                    countryData
                )
            )
            currenciesDataList.add(
                bundleDataToEntities(
                    Constants.SWEDISH_CURRENCY,
                    currenciesData,
                    yesterdayCurrenciesData,
                    countryData
                )
            )
            currenciesDataList.add(
                bundleDataToEntities(
                    Constants.INDONESIA_CURRENCY,
                    currenciesData,
                    yesterdayCurrenciesData,
                    countryData
                )
            )
            currenciesDataList.add(
                bundleDataToEntities(
                    Constants.INDIA_CURRENCY,
                    currenciesData,
                    yesterdayCurrenciesData,
                    countryData
                )
            )
            currenciesDataList.add(
                bundleDataToEntities(
                    Constants.BRAZILLIAN_CURRENCY,
                    currenciesData,
                    yesterdayCurrenciesData,
                    countryData
                )
            )
            currenciesDataList.add(
                bundleDataToEntities(
                    Constants.RUSSIAN_CURRENCY,
                    currenciesData,
                    yesterdayCurrenciesData,
                    countryData
                )
            )
            currenciesDataList.add(
                bundleDataToEntities(
                    Constants.CROATIAN_CURRENCY,
                    currenciesData,
                    yesterdayCurrenciesData,
                    countryData
                )
            )
            currenciesDataList.add(
                bundleDataToEntities(
                    Constants.JAPAN_CURRENCY,
                    currenciesData,
                    yesterdayCurrenciesData,
                    countryData
                )
            )
            currenciesDataList.add(
                bundleDataToEntities(
                    Constants.THAI_CURRENCY,
                    currenciesData,
                    yesterdayCurrenciesData,
                    countryData
                )
            )
            currenciesDataList.add(
                bundleDataToEntities(
                    Constants.SWISH_CURRENCY,
                    currenciesData,
                    yesterdayCurrenciesData,
                    countryData
                )
            )
            currenciesDataList.add(
                bundleDataToEntities(
                    Constants.EUROPEAN_CURRENCY,
                    currenciesData,
                    yesterdayCurrenciesData,
                    countryData
                )
            )
            currenciesDataList.add(
                bundleDataToEntities(
                    Constants.MALAYSIAN_CURRENCY,
                    currenciesData,
                    yesterdayCurrenciesData,
                    countryData
                )
            )
            currenciesDataList.add(
                bundleDataToEntities(
                    Constants.BULGARIAN_CURRENCY,
                    currenciesData,
                    yesterdayCurrenciesData,
                    countryData
                )
            )
            currenciesDataList.add(
                bundleDataToEntities(
                    Constants.TURKISH_CURRENCY,
                    currenciesData,
                    yesterdayCurrenciesData,
                    countryData
                )
            )
            currenciesDataList.add(
                bundleDataToEntities(
                    Constants.CHINESE_CURRENCY,
                    currenciesData,
                    yesterdayCurrenciesData,
                    countryData
                )
            )
            currenciesDataList.add(
                bundleDataToEntities(
                    Constants.NORWEGIAN_CURRENCY,
                    currenciesData,
                    yesterdayCurrenciesData,
                    countryData
                )
            )
            currenciesDataList.add(
                bundleDataToEntities(
                    Constants.NEW_ZEALAND_CURRENCY,
                    currenciesData,
                    yesterdayCurrenciesData,
                    countryData
                )
            )
            currenciesDataList.add(
                bundleDataToEntities(
                    Constants.SOUTH_AFRICAN_CURRENCY,
                    currenciesData,
                    yesterdayCurrenciesData,
                    countryData
                )
            )
            currenciesDataList.add(
                bundleDataToEntities(
                    Constants.US_CURRENCY,
                    currenciesData,
                    yesterdayCurrenciesData,
                    countryData
                )
            )
            currenciesDataList.add(
                bundleDataToEntities(
                    Constants.MEXICAN_CURRENCY,
                    currenciesData,
                    yesterdayCurrenciesData,
                    countryData
                )
            )
            currenciesDataList.add(
                bundleDataToEntities(
                    Constants.SINGAPORE_CURRENCY,
                    currenciesData,
                    yesterdayCurrenciesData,
                    countryData
                )
            )
            currenciesDataList.add(
                bundleDataToEntities(
                    Constants.AUSTRALIAN_CURRENCY,
                    currenciesData,
                    yesterdayCurrenciesData,
                    countryData
                )
            )
            currenciesDataList.add(
                bundleDataToEntities(
                    Constants.ISRAEL_CURRENCY,
                    currenciesData,
                    yesterdayCurrenciesData,
                    countryData
                )
            )
            currenciesDataList.add(
                bundleDataToEntities(
                    Constants.SOUTH_KOREAN_CURRENCY,
                    currenciesData,
                    yesterdayCurrenciesData,
                    countryData
                )
            )
            currenciesDataList.add(
                bundleDataToEntities(
                    Constants.POLAND_CURRENCY,
                    currenciesData,
                    yesterdayCurrenciesData,
                    countryData
                )
            )
        } catch (e: Exception) {
            Timber.tag(Constants.TAG).e(e.toString())
            ToastUtils.showLong("Failed to Load Data, try Refreshing")
        }
        return currenciesDataList
    }

    fun startRecyclerLoading(
        shimmerFrameLayout: ShimmerFrameLayout,
        swipeRefreshLayout: SwipeRefreshLayout
    ) {
        shimmerFrameLayout.startShimmer()
        shimmerFrameLayout.visibility = View.VISIBLE
        swipeRefreshLayout.visibility = View.GONE
    }

    fun stopRecyclerLoading(
        shimmerFrameLayout: ShimmerFrameLayout,
        swipeRefreshLayout: SwipeRefreshLayout
    ) {
        shimmerFrameLayout.stopShimmer()
        shimmerFrameLayout.visibility = View.GONE
        swipeRefreshLayout.visibility = View.VISIBLE
        swipeRefreshLayout.isRefreshing = false
    }
}