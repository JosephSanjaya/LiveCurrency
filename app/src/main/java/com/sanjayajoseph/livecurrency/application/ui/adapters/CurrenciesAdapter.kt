package com.sanjayajoseph.livecurrency.application.ui.adapters

import android.net.Uri
import androidx.core.content.ContextCompat
import com.blankj.utilcode.util.ConvertUtils
import com.blankj.utilcode.util.StringUtils
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.fasterxml.jackson.databind.ObjectMapper
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou
import com.sanjayajoseph.livecurrency.R
import com.sanjayajoseph.livecurrency.api.models.currencies.entities.CurrenciesEntity
import com.sanjayajoseph.livecurrency.application.common.Constants
import kotlinx.android.synthetic.main.home_adapter_layout.view.*
import timber.log.Timber
import java.lang.Exception
import java.text.DecimalFormat
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*


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
                helper.setText(R.id.tvSymbol,item?.data?.symbols.toString())
                    .setText(R.id.tvValue,nf.format(item?.data?.value))
                    .setText(R.id.tvDiff,nf.format(item?.diffValue)+ " %")
                    .setText(R.id.tvStateName,item?.countryData?.name)
                    .setImageDrawable(R.id.ivRates, ContextCompat.getDrawable(mContext, R.drawable.ic_trending_down))
                    .setTextColor(R.id.tvDiff, ContextCompat.getColor(mContext, R.color.colorRed))
                try {
                    val requestBuilder = GlideToVectorYou
                        .init()
                        .with(mContext)
                        .requestBuilder
                    requestBuilder
                        .load(Uri.parse(item?.countryData?.flag))
                        .centerCrop()
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .apply(RequestOptions().circleCrop())
                        .into(helper.getView(R.id.ivFlag))
                }catch (e: Exception)
                {
                    Timber.tag(Constants.TAG).e(e)
                }
            }
            Constants.HOME_CURRENCIES_TYPE_UP -> {
                helper.setText(R.id.tvSymbol,item?.data?.symbols.toString())
                    .setText(R.id.tvValue,nf.format(item?.data?.value))
                    .setText(R.id.tvDiff,nf.format(item?.diffValue)+ " %")
                    .setText(R.id.tvStateName,item?.countryData?.name)
                    .setImageDrawable(R.id.ivRates, ContextCompat.getDrawable(mContext, R.drawable.ic_trending_up))
                    .setTextColor(R.id.tvDiff, ContextCompat.getColor(mContext, R.color.colorGreen))
                try {
                    val requestBuilder = GlideToVectorYou
                        .init()
                        .with(mContext)
                        .requestBuilder
                    requestBuilder
                        .load(Uri.parse(item?.countryData?.flag))
                        .centerCrop()
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .apply(RequestOptions().circleCrop())
                        .into(helper.getView(R.id.ivFlag))
                }catch (e: Exception)
                {
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