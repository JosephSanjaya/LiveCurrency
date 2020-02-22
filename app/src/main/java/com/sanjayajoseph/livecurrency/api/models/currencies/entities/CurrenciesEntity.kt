package com.sanjayajoseph.livecurrency.api.models.currencies.entities

import com.chad.library.adapter.base.entity.MultiItemEntity


/*
* @author Joseph Sanjaya on 2/22/2020.
* Linkedin : https://www.linkedin.com/in/josephsanjaya/
* Github : https://github.com/JosephSanjaya
*/

data class CurrenciesEntity(
    var data: CurrenciesData? = null,
    var type: Int = 0
) : MultiItemEntity {
    override fun getItemType(): Int {
        return type
    }
}