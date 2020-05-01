package com.sanjayajoseph.livecurrency.application.ui.adapters

import android.net.Uri
import android.widget.ImageView
import coil.api.load
import coil.transform.CircleCropTransformation
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.sanjayajoseph.livecurrency.R
import com.sanjayajoseph.livecurrency.api.models.countries.CountriesResponse
import com.sanjayajoseph.livecurrency.application.common.Constants
import timber.log.Timber


/*
* @author Joseph Sanjaya on 2/22/2020.
* Linkedin : https://www.linkedin.com/in/josephsanjaya/
* Github : https://github.com/JosephSanjaya
*/

class CountriesAdapter(layoutResId: Int, data: List<CountriesResponse?>?) :
    BaseQuickAdapter<CountriesResponse, BaseViewHolder>(
        layoutResId,
        data as MutableList<CountriesResponse>?
    ) {

    override fun convert(helper: BaseViewHolder, item: CountriesResponse?) {
        helper.setText(R.id.tvName, item?.name)
            .setText(R.id.tvCurrency, item?.currencies?.get(0)?.code)
            .addOnClickListener(R.id.llRoot)
        helper.getView<ImageView>(R.id.ivCountriesFlag).load(Uri.parse(item?.flag)){
            transformations(CircleCropTransformation())
        }
        Timber.tag(Constants.TAG).i("Set Image")
    }

}