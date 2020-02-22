package com.sanjayajoseph.livecurrency.services.internetmanager.component

import com.sanjayajoseph.livecurrency.services.internetmanager.other.Monitor

/*
* @author Joseph Sanjaya on 2/22/2020.
* Linkedin : https://www.linkedin.com/in/josephsanjaya/
* Github : https://github.com/JosephSanjaya
*/

class NoopMonitor :
    Monitor {
    override fun onStart() { //no-op
    }

    override fun onStop() { //no-op
    }
}