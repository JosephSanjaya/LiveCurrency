package com.sanjayajoseph.livecurrency.services.internetmanager.other

import android.content.Context
import androidx.annotation.NonNull

/*
* @author Joseph Sanjaya on 2/22/2020.
* Linkedin : https://www.linkedin.com/in/josephsanjaya/
* Github : https://github.com/JosephSanjaya
*/

interface MonitorFactory {
    @NonNull
    fun create(
        @NonNull context: Context?,
        connectionType: Int,
        @NonNull listener: Monitor.ConnectivityListener?
    ): Monitor?
}
