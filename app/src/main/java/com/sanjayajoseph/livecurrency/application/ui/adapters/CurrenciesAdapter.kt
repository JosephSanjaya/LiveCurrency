package com.sanjayajoseph.livecurrency.application.ui.adapters

import android.net.Uri
import android.widget.ImageView
import androidx.core.content.ContextCompat
import coil.api.load
import coil.transform.CircleCropTransformation
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.sanjayajoseph.livecurrency.R
import com.sanjayajoseph.livecurrency.api.models.currencies.entities.CurrenciesEntity
import com.sanjayajoseph.livecurrency.application.common.Constants
import timber.log.Timber
import java.text.NumberFormat


/*
* @author Joseph Sanjaya on 2/22/2020.
* Linkedin : https://www.linkedin.com/in/josephsanjaya/
* Github : https://github.com/JosephSanjaya
*/


class CurrenciesAdapter(data: List<CurrenciesEntity?>?) :
    BaseMultiItemQuickAdapter<CurrenciesEntity?, BaseViewHolder?>(data) {
    override fun convert(helper: BaseViewHolder?, item: CurrenciesEntity?) {
        val nf: NumberFormat = NumberFormat.getInstance()
        nf.maximumFractionDigits = 6
        nf.isGroupingUsed = false
        when (helper?.itemViewType) {
            Constants.HOME_CURRENCIES_TYPE_DOWN -> {
                helper.setText(R.id.tvSymbol, item?.data?.symbols.toString())
                    .setText(R.id.tvValue, nf.format(item?.data?.value))
                    .setText(R.id.tvDiff, nf.format(item?.diffValue) + " %")
                    .setText(R.id.tvStateName, item?.countryData?.alpha3Code)
                    .setImageDrawable(
                        R.id.ivRates,
                        ContextCompat.getDrawable(mContext, R.drawable.ic_trending_down)
                    )
                    .setTextColor(R.id.tvDiff, ContextCompat.getColor(mContext, R.color.colorRed))
//                    .addOnClickListener(R.id.flRoot)
                try {
                    helper.getView<ImageView>(R.id.ivFlag).load((Uri.parse(item?.countryData?.flag))){
                        transformations(CircleCropTransformation())
                    }
                } catch (e: Exception) {
                    Timber.tag(Constants.TAG).e(e)
                }
            }
            Constants.HOME_CURRENCIES_TYPE_UP -> {
                helper.setText(R.id.tvSymbol, item?.data?.symbols.toString())
                    .setText(R.id.tvValue, nf.format(item?.data?.value))
                    .setText(R.id.tvDiff, nf.format(item?.diffValue) + " %")
                    .setText(R.id.tvStateName, item?.countryData?.alpha3Code)
                    .setImageDrawable(
                        R.id.ivRates,
                        ContextCompat.getDrawable(mContext, R.drawable.ic_trending_up)
                    )
                    .setTextColor(R.id.tvDiff, ContextCompat.getColor(mContext, R.color.colorGreen))
//                    .addOnClickListener(R.id.flRoot)
                try {
                    helper.getView<ImageView>(R.id.ivFlag).load((Uri.parse(item?.countryData?.flag))){
                        transformations(CircleCropTransformation())
                    }
                } catch (e: Exception) {
                    Timber.tag(Constants.TAG).e(e)
                }
            }
        }
    }

    init {
        addItemType(Constants.HOME_CURRENCIES_TYPE_DOWN, R.layout.home_adapter_layout)
        addItemType(Constants.HOME_CURRENCIES_TYPE_UP, R.layout.home_adapter_layout)
    }
}