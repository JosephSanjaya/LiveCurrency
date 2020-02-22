package com.sanjayajoseph.livecurrency.application.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sanjayajoseph.livecurrency.api.interfaces.Unsubscribe
import com.sanjayajoseph.livecurrency.api.models.Errors


/*
* @author Joseph Sanjaya on 2/22/2020.
* Linkedin : https://www.linkedin.com/in/josephsanjaya/
* Github : https://github.com/JosephSanjaya
*/

open class BaseViewModel<T : Unsubscribe>(private val clientRepository: T) : ViewModel() {
    private val errors: MutableLiveData<Errors> = MutableLiveData()

    override fun onCleared() {
        clientRepository.unSubscribe()
        super.onCleared()
    }


    protected fun processError(error: Throwable) {
        val er = ArrayList<String>(1)
        er.add(error.message ?: "Error Identification")
        errors.value = Errors(er)
    }

    fun errorsClear() {
        errors.value =
            Errors(arrayListOf())
    }

}