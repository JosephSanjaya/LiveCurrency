package com.sanjayajoseph.livecurrency.application.base

import androidx.multidex.MultiDexApplication
import com.crashlytics.android.Crashlytics
import com.crashlytics.android.core.CrashlyticsCore
import com.facebook.drawee.backends.pipeline.Fresco
import com.orhanobut.hawk.Hawk
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.FormatStrategy
import com.orhanobut.logger.Logger
import com.orhanobut.logger.PrettyFormatStrategy
import com.sanjayajoseph.livecurrency.BuildConfig
import com.sanjayajoseph.livecurrency.application.modules.AppModule
import io.fabric.sdk.android.Fabric
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber


/*
* @author Joseph Sanjaya on 2/22/2020.
* Linkedin : https://www.linkedin.com/in/josephsanjaya/
* Github : https://github.com/JosephSanjaya
*/


class BaseApplication : MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()
        Hawk.init(this).build()
        Fresco.initialize(this)
        if (BuildConfig.DEBUG) {
            Timber.plant(object : Timber.DebugTree() {
                override fun log(
                    priority: Int,
                    tag: String?,
                    message: String,
                    t: Throwable?
                ) {
                    Logger.log(priority, tag, message, t)
                }
            })
            val formatStrategy: FormatStrategy = PrettyFormatStrategy.newBuilder()
                .tag("Finansialku-Log")
                .build()
            Logger.addLogAdapter(AndroidLogAdapter(formatStrategy))
            Timber.plant(Timber.DebugTree())
        }

        val crashlyticsKit = Crashlytics.Builder()
            .core(CrashlyticsCore.Builder().disabled(BuildConfig.DEBUG).build())
            .build()
        Fabric.with(this, crashlyticsKit)

        startKoin {
            androidLogger()
            androidContext(applicationContext)
            modules(listOf(AppModule))
        }

    }

}