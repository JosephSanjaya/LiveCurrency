package com.sanjayajoseph.livecurrency.api.interfaces


/*
* @author Joseph Sanjaya on 2/28/2020.
* Linkedin : https://www.linkedin.com/in/josephsanjaya/
* Github : https://github.com/JosephSanjaya
*/

sealed class ApiResult<out T : Any> {
    class Success<out T : Any>(val data: T) : ApiResult<T>()
    class Error(val exception: Throwable) : ApiResult<Nothing>()
}