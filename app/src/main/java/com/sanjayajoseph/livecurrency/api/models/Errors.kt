package com.sanjayajoseph.livecurrency.api.models


/*
* @author Joseph Sanjaya on 2/22/2020.
* Linkedin : https://www.linkedin.com/in/josephsanjaya/
* Github : https://github.com/JosephSanjaya
*/

data class Errors(val errors: ArrayList<String>) {
    override fun toString(): String {
        return errors.toString()
    }
}