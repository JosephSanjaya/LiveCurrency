package com.sanjayajoseph.livecurrency.services.internetmanager.other

/*
* @author Joseph Sanjaya on 2/22/2020.
* Linkedin : https://www.linkedin.com/in/josephsanjaya/
* Github : https://github.com/JosephSanjaya
*/

interface Monitor :
    LifecycleListener {
    interface ConnectivityListener {
        fun onConnectivityChanged(
            connectionType: Int,
            isConnected: Boolean,
            isFast: Boolean
        )
    }
}