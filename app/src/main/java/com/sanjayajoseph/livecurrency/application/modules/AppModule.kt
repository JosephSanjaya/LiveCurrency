package com.sanjayajoseph.livecurrency.application.modules

import com.sanjayajoseph.livecurrency.BuildConfig
import com.sanjayajoseph.livecurrency.api.interfaces.APICountriesInterface
import com.sanjayajoseph.livecurrency.api.interfaces.APICurrenciesInterface
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module

/*
* @author Joseph Sanjaya on 2/22/2020.
* Linkedin : https://www.linkedin.com/in/josephsanjaya/
* Github : https://github.com/JosephSanjaya
*/

val AppModule: Module = module {
    single {
        APICurrenciesInterface(
            BuildConfig.BASE_CURRENCY_API,
            androidContext()
        )
    }
    single {
        APICountriesInterface(
            BuildConfig.BASE_COUNTRY_API,
            androidContext()
        )
    }
}