package com.sanjayajoseph.livecurrency.application.common

import com.sanjayajoseph.livecurrency.BuildConfig
import com.sanjayajoseph.livecurrency.api.interfaces.APICountriesInterface
import com.sanjayajoseph.livecurrency.api.interfaces.APIInterface
import com.sanjayajoseph.livecurrency.api.repository.CurrenciesRepository
import com.sanjayajoseph.livecurrency.application.viewmodel.CurrenciesViewModel
import io.reactivex.disposables.CompositeDisposable
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

/*
* @author Joseph Sanjaya on 2/22/2020.
* Linkedin : https://www.linkedin.com/in/josephsanjaya/
* Github : https://github.com/JosephSanjaya
*/

object Modules {
    val AppModule: Module = module {
        single {
            APIInterface(
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
        single {
            CurrenciesRepository(
                get(),
                CompositeDisposable()
            )
        }
        viewModel {
            CurrenciesViewModel(get())
        }
    }
}