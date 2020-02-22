package com.sanjayajoseph.livecurrency.application.base

import android.app.Activity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.google.android.material.snackbar.Snackbar
import com.sanjayajoseph.livecurrency.R
import com.sanjayajoseph.livecurrency.application.common.Constants
import com.sanjayajoseph.livecurrency.databinding.BaseContainerBinding
import com.sanjayajoseph.livecurrency.services.internetmanager.InternetManager
import com.sanjayajoseph.livecurrency.services.internetmanager.other.Monitor


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