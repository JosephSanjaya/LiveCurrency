package com.sanjayajoseph.livecurrency.application.ui.fragments

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.blankj.utilcode.util.ToastUtils
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.chad.library.adapter.base.BaseQuickAdapter
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou
import com.sanjayajoseph.livecurrency.R
import com.sanjayajoseph.livecurrency.api.interfaces.APICountriesInterface
import com.sanjayajoseph.livecurrency.api.models.countries.CountriesResponse
import com.sanjayajoseph.livecurrency.api.models.currencies.base.CurrenciesResponse
import com.sanjayajoseph.livecurrency.api.models.currencies.entities.CurrenciesEntity
import com.sanjayajoseph.livecurrency.application.base.BaseFragment
import com.sanjayajoseph.livecurrency.application.common.Constants
import com.sanjayajoseph.livecurrency.application.common.Functions
import com.sanjayajoseph.livecurrency.application.common.Hawk
import com.sanjayajoseph.livecurrency.application.ui.adapters.CountriesAdapter
import com.sanjayajoseph.livecurrency.application.ui.adapters.CurrenciesAdapter
import com.sanjayajoseph.livecurrency.application.viewmodel.CountriesViewModel
import com.sanjayajoseph.livecurrency.application.viewmodel.CurrenciesViewModel
import com.sanjayajoseph.livecurrency.databinding.HomeLayoutBinding
import kotlinx.android.synthetic.main.home_layout.*
import kotlinx.android.synthetic.main.view_dialog_recycler.view.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel


/*
* @author Joseph Sanjaya on 2/22/2020.
* Linkedin : https://www.linkedin.com/in/josephsanjaya/
* Github : https://github.com/JosephSanjaya
*/

class HomeFragment : BaseFragment() {
    private val currenciesViewModel: CurrenciesViewModel by viewModel()
    private val countriesViewModel: CountriesViewModel by viewModel()
    private lateinit var mContext: Context
    private lateinit var mActivity: Activity
    private lateinit var binding: HomeLayoutBinding
    private var currenciesDataList: ArrayList<CurrenciesEntity> = ArrayList()
    private var countriesDataList: ArrayList<CountriesResponse> = ArrayList()
    private var yesterdayCurrenciesData: CurrenciesResponse? = null
    private var currenciesData: CurrenciesResponse? = null
    private lateinit var currenciesAdapter: CurrenciesAdapter
    private var selectedType: String = Constants.INDONESIA_CURRENCY
    private var mRecyclerDialog: AlertDialog? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<HomeLayoutBinding>(
            inflater,
            R.layout.home_layout, container, false
        ).apply {
        }
        setupListener()
        loadCurrencies(Constants.INDONESIA_CURRENCY)
        return binding.root
    }

    private fun showRecyclerDialog(
        mContext: Context,
        title: String,
        adapter: BaseQuickAdapter<*, *>
    ) {
        try {
            val dialogBuilder =
                AlertDialog.Builder(mContext)
            val view =
                View.inflate(mContext, R.layout.view_dialog_recycler, null)
            view.spinerTitle.text = title
            view.close.setOnClickListener {
                if (mRecyclerDialog!!.isShowing)
                    mRecyclerDialog!!.dismiss()
            }
            adapter.openLoadAnimation()
            view.rvData.layoutManager = LinearLayoutManager(mContext)
            view.rvData.adapter = adapter
            adapter.notifyDataSetChanged()
            adapter.setOnItemChildClickListener { adapter, view, position ->
                if (view.id == R.id.llRoot) {
                    if (mRecyclerDialog != null && mRecyclerDialog!!.isShowing)
                        mRecyclerDialog!!.dismiss()
                    if (countriesDataList[position].name == "Singapore") {
                        val requestBuilder = GlideToVectorYou
                            .init()
                            .with(mContext)
                            .requestBuilder
                        requestBuilder
                            .load(Uri.parse(countriesDataList[position].flag))
                            .centerCrop()
                            .transition(DrawableTransitionOptions.withCrossFade())
                            .apply(RequestOptions().circleCrop())
                            .into(binding.ivCountriesFlag)
                        selectedType =
                            countriesDataList[position].currencies?.get(1)?.code.toString()
                        loadCurrencies(selectedType)
                        binding.tvSelectedCurrency.text =
                            countriesDataList[position].currencies?.get(1)?.code.toString()
                        binding.tvSelectedState.text =
                            countriesDataList[position].alpha2Code
                    } else {
                        val requestBuilder = GlideToVectorYou
                            .init()
                            .with(mContext)
                            .requestBuilder
                        requestBuilder
                            .load(Uri.parse(countriesDataList[position].flag))
                            .centerCrop()
                            .transition(DrawableTransitionOptions.withCrossFade())
                            .apply(RequestOptions().circleCrop())
                            .into(binding.ivCountriesFlag)
                        selectedType =
                            countriesDataList[position].currencies?.get(0)?.code.toString()
                        loadCurrencies(selectedType)
                        binding.tvSelectedCurrency.text =
                            countriesDataList[position].currencies?.get(0)?.code.toString()
                        binding.tvSelectedState.text =
                            countriesDataList[position].alpha2Code
                    }
                }
            }
            dialogBuilder.setView(view)
            dialogBuilder.setCancelable(true)
            mRecyclerDialog = dialogBuilder.create()
            mRecyclerDialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            mRecyclerDialog!!.window!!.attributes.windowAnimations =
                R.style.FadeDialogAnimation
            mRecyclerDialog!!.show()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


    private fun setupListener() {
        getCountries()
        binding.llState.setOnClickListener {
            showRecyclerDialog(
                mContext,
                "Countries",
                CountriesAdapter(R.layout.view_countries_list, countriesDataList)
            )
        }
        binding.srlHome.setOnRefreshListener {
            loadCurrencies(selectedType)
        }
    }

    private fun getCountries() {
        countriesDataList = ArrayList()
        setupCurrenciesAdapter()
        getCountriesRemoteData(Constants.COUNTRIES_SUPPORTED_CODES)
    }

    private fun getCountriesRemoteData(alpha : String)
    {
        if (countriesViewModel.countriesData.hasObservers()) countriesViewModel.countriesData.removeObservers(
            viewLifecycleOwner
        )
        countriesViewModel.countriesData.observe(viewLifecycleOwner, Observer { data ->
            if (data != null) {
                countriesDataList = data
            }
        })
        if (countriesViewModel.loadingCountries.hasObservers()) countriesViewModel.loadingCountries.removeObservers(
            viewLifecycleOwner
        )
        countriesViewModel.loadingCountries.observe(
            viewLifecycleOwner,
            Observer { loadingStatus ->
                when (loadingStatus) {
                    Constants.LOADING_STATUS_LOAD -> return@Observer
                    Constants.LOADING_STATUS_SUCCESS -> countriesDataList.remove(countriesDataList.find { data -> data == null })
                    Constants.LOADING_STATUS_FAILED -> {
                        ToastUtils.showLong("Failed to Load Data, try Refreshing")
                    }
                    else -> {
                        ToastUtils.showLong("Failed to Load Data, Unknown Error")
                    }
                }
            })
        countriesViewModel.getCountriesByAlpha(alpha)
    }

    private fun setupCurrenciesAdapter() {
        currenciesAdapter =
            CurrenciesAdapter(
                currenciesDataList
            )
        currenciesAdapter.openLoadAnimation()
        currenciesAdapter.notifyDataSetChanged()
        binding.rvHome.layoutManager = LinearLayoutManager(mContext)
        binding.rvHome.adapter = currenciesAdapter
        currenciesAdapter.notifyDataSetChanged()
    }

    private fun loadCurrencies(type: String) {
        currenciesDataList = ArrayList()
        setupCurrenciesAdapter()
        if (currenciesViewModel.currenciesData.hasObservers()) currenciesViewModel.currenciesData.removeObservers(
            viewLifecycleOwner
        )
        currenciesViewModel.currenciesData.observe(viewLifecycleOwner, Observer { data ->
            currenciesData = data
        })
        if (currenciesViewModel.loadingLatestCurrency.hasObservers()) currenciesViewModel.loadingLatestCurrency.removeObservers(
            viewLifecycleOwner
        )
        currenciesViewModel.loadingLatestCurrency.observe(
            viewLifecycleOwner,
            Observer { loadingStatus ->
                when (loadingStatus) {
                    Constants.LOADING_STATUS_LOAD -> Functions.startRecyclerLoading(
                        binding.shimmerViewContainer,
                        binding.srlHome
                    )
                    Constants.LOADING_STATUS_SUCCESS -> loadYesterdayCurrencies(
                        type,
                        Functions.getYesterdayDate(currenciesData?.date!!)
                    )
                    Constants.LOADING_STATUS_FAILED -> {
                        ToastUtils.showLong("Failed to Load Data, try Refreshing")
                        Functions.stopRecyclerLoading(binding.shimmerViewContainer, binding.srlHome)
                    }
                    else -> {
                        ToastUtils.showLong("Failed to Load Data, Unknown Error")
                        Functions.stopRecyclerLoading(binding.shimmerViewContainer, binding.srlHome)
                    }
                }
            })
        currenciesViewModel.getLatestCurrencyRates(type, "")
    }

    private fun loadYesterdayCurrencies(type: String, date: String) {
        if (currenciesViewModel.dateCurrenciesData.hasObservers()) currenciesViewModel.dateCurrenciesData.removeObservers(
            viewLifecycleOwner
        )
        currenciesViewModel.dateCurrenciesData.observe(viewLifecycleOwner, Observer { data ->
            yesterdayCurrenciesData = data
        })
        if (currenciesViewModel.loadingCurrencyByDate.hasObservers()) currenciesViewModel.loadingCurrencyByDate.removeObservers(
            viewLifecycleOwner
        )
        currenciesViewModel.loadingCurrencyByDate.observe(
            viewLifecycleOwner,
            Observer { loadingStatus ->
                when (loadingStatus) {
                    Constants.LOADING_STATUS_LOAD -> return@Observer
                    Constants.LOADING_STATUS_SUCCESS -> {
                        if (binding.srlHome.isRefreshing)
                            srlHome.isRefreshing = false
                        currenciesDataList.clear()
                        currenciesDataList.addAll(
                            Functions.bundleDataToArray(
                                currenciesData!!,
                                yesterdayCurrenciesData!!,
                                countriesDataList
                            )
                        )
                        currenciesDataList.remove(currenciesDataList.find { data -> data.data?.symbols == type })
                        currenciesAdapter.notifyDataSetChanged()
                        Functions.stopRecyclerLoading(binding.shimmerViewContainer, binding.srlHome)
                    }
                    Constants.LOADING_STATUS_FAILED -> {
                        if (binding.srlHome.isRefreshing)
                            srlHome.isRefreshing = false
                        ToastUtils.showLong("Failed to Load Data, try Refreshing")
                        Functions.stopRecyclerLoading(binding.shimmerViewContainer, binding.srlHome)
                    }
                    else -> {
                        if (binding.srlHome.isRefreshing)
                            srlHome.isRefreshing = false
                        ToastUtils.showLong("Failed to Load Data, Unknown Error")
                        Functions.stopRecyclerLoading(binding.shimmerViewContainer, binding.srlHome)
                    }
                }
            })
        currenciesViewModel.getCurrencyRatesByDate(
            Functions.getYesterdayDate(date),
            type, ""
        )
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
        mActivity = context as Activity
    }
}