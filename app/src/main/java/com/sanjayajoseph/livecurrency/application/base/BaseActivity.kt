package com.sanjayajoseph.livecurrency.application.base

import android.app.Activity
import android.content.Context
import android.opengl.Visibility
import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import com.google.android.material.snackbar.Snackbar
import com.sanjayajoseph.livecurrency.BuildConfig
import com.sanjayajoseph.livecurrency.R
import com.sanjayajoseph.livecurrency.application.common.Constants
import com.sanjayajoseph.livecurrency.databinding.BaseContainerBinding
import com.sanjayajoseph.livecurrency.services.internetmanager.InternetManager
import com.sanjayajoseph.livecurrency.services.internetmanager.other.Monitor
import kotlinx.android.synthetic.main.base_container.*

/*
* @author Joseph Sanjaya on 2/22/2020.
* Linkedin : https://www.linkedin.com/in/josephsanjaya/
* Github : https://github.com/JosephSanjaya
*/

class BaseActivity : AppCompatActivity() {
    private var initFlag = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataBindingUtil.setContentView<BaseContainerBinding>(
            this,
            R.layout.base_container
        )
        connectivityMonitor(this)
        setupAds()
    }

    private fun setupAds()
    {
        if(BuildConfig.TYPE == Constants.FREE_TYPE)
        {
            MobileAds.initialize(this) {}
            adView.visibility = VISIBLE
            val adRequest = AdRequest.Builder().build()
            adView.loadAd(adRequest)
        }
    }

    private fun connectivityMonitor(activity: Activity) {
        InternetManager.from(this)?.monitor(object : Monitor.ConnectivityListener {
            override fun onConnectivityChanged(
                connectionType: Int,
                isConnected: Boolean,
                isFast: Boolean
            ) {
                if (isConnected) {
                    if (initFlag) initFlag = false else
                        Snackbar.make(
                            findViewById(android.R.id.content),
                            Constants.CONNECTIVITY_ON,
                            Snackbar.LENGTH_LONG
                        )
                            .setBackgroundTint(ContextCompat.getColor(activity, R.color.colorGreen))
                            .setActionTextColor(
                                ContextCompat.getColor(
                                    activity,
                                    R.color.colorWhite_75
                                )
                            )
                            .show()
                } else {
                    if (initFlag) initFlag = false else
                        Snackbar.make(
                            findViewById(android.R.id.content),
                            Constants.CONNECTIVITY_OFF,
                            Snackbar.LENGTH_LONG
                        )
                            .setBackgroundTint(ContextCompat.getColor(activity, R.color.colorRed))
                            .setActionTextColor(
                                ContextCompat.getColor(
                                    activity,
                                    R.color.colorWhite_75
                                )
                            )
                            .show()
                }
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        InternetManager.from(this)?.stop()
    }
}