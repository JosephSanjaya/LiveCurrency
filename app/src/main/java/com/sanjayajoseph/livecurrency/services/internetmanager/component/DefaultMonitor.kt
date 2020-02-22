package com.sanjayajoseph.livecurrency.services.internetmanager.component

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Handler
import android.os.Looper
import android.util.Log
import com.sanjayajoseph.livecurrency.services.internetmanager.other.Monitor

/*
* @author Joseph Sanjaya on 2/22/2020.
* Linkedin : https://www.linkedin.com/in/josephsanjaya/
* Github : https://github.com/JosephSanjaya
*/

class DefaultMonitor @JvmOverloads constructor(
    private val context: Context,
    private val listener: Monitor.ConnectivityListener,
    private val connectionType: Int = -1
) :
    Monitor {
    private val mainHandler = Handler(Looper.getMainLooper())
    private var isConnected = false
    private var isRegistered = false
    private val connectivityReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(
            context: Context,
            intent: Intent
        ) {
            val wasConnected = isConnected
            isConnected =
                NetworkUtil.isConnected(
                    context,
                    connectionType
                )
            if (wasConnected != isConnected) {
                emitEvent()
            }
        }
    }

    private fun emitEvent() {
        Log.i("Monitor", "Network change")
        mainHandler.post {
            listener.onConnectivityChanged(
                connectionType,
                isConnected,
                isConnected && NetworkUtil.isConnectionFast(
                    context
                )
            )
        }
    }

    private fun register() {
        if (isRegistered) {
            return
        }
        Log.i("Monitor", "Registering")
        isConnected =
            NetworkUtil.isConnected(
                context,
                connectionType
            )
        //emit once
        emitEvent()
        context.registerReceiver(
            connectivityReceiver,
            IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        )
        isRegistered = true
    }

    private fun unregister() {
        if (!isRegistered) {
            return
        }
        Log.i("Monitor", "Unregistering")
        context.unregisterReceiver(connectivityReceiver)
        isRegistered = false
    }

    override fun onStart() {
        register()
    }

    override fun onStop() {
        unregister()
    }

}

