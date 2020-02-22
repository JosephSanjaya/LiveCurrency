package com.sanjayajoseph.livecurrency.services.internetmanager.component

import java.util.*

/*
* @author Joseph Sanjaya on 2/22/2020.
* Linkedin : https://www.linkedin.com/in/josephsanjaya/
* Github : https://github.com/JosephSanjaya
*/

object Util {
    fun <T> getSnapshot(other: Collection<T>): List<T> {
        val result: MutableList<T> = ArrayList(other.size)
        for (item in other) {
            result.add(item)
        }
        return result
    }
}