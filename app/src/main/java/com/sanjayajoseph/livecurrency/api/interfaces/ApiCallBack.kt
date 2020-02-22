package com.sanjayajoseph.livecurrency.api.interfaces

/*
* @author Joseph Sanjaya on 2/22/2020.
* Linkedin : https://www.linkedin.com/in/josephsanjaya/
* Github : https://github.com/JosephSanjaya
*/

interface ApiCallBack<in T> {
    fun onComplete() {}
    fun onStart() {}
    fun onError(error: Throwable)
    fun onSucess(response: T)
}