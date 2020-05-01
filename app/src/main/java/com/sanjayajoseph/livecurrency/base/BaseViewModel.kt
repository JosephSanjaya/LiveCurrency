package com.sanjayajoseph.livecurrency.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel


/*
* @author Joseph Sanjaya on 2/28/2020.
* Linkedin : https://www.linkedin.com/in/josephsanjaya/
* Github : https://github.com/JosephSanjaya
*/

abstract class BaseViewModel : ViewModel() {

    protected val mainScope = CoroutineScope(Dispatchers.Main)
    protected val ioScope = CoroutineScope(Dispatchers.IO)

    override fun onCleared() {
        super.onCleared()
        mainScope.coroutineContext.cancel()
        ioScope.coroutineContext.cancel()
    }
}