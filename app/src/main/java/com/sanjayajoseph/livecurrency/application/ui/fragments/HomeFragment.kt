package com.sanjayajoseph.livecurrency.application.ui.fragments

import android.app.AlertDialog
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
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
import coil.api.load
import coil.transform.CircleCropTransformation
import com.blankj.utilcode.util.ToastUtils
import com.chad.library.adapter.base.BaseQuickAdapter
import com.sanjayajoseph.livecurrency.R
import com.sanjayajoseph.livecurrency.api.models.countries.CountriesResponse
import com.sanjayajoseph.livecurrency.api.models.currencies.base.CurrenciesResponse
import com.sanjayajoseph.livecurrency.api.models.currencies.entities.CurrenciesEntity
import com.sanjayajoseph.livecurrency.application.common.Constants
import com.sanjayajoseph.livecurrency.application.common.Functions
import com.sanjayajoseph.livecurrency.application.ui.adapters.CountriesAdapter
import com.sanjayajoseph.livecurrency.application.ui.adapters.CurrenciesAdapter
import com.sanjayajoseph.livecurrency.application.viewmodel.CountriesViewModel
import com.sanjayajoseph.livecurrency.application.viewmodel.CurrenciesViewModel
import com.sanjayajoseph.livecurrency.base.BaseFragment
import com.sanjayajoseph.livecurrency.databinding.HomeLayoutBinding
import kotlinx.android.synthetic.main.home_layout.*
import kotlinx.android.synthetic.main.view_dialog_recycler.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel

/*
* @author Joseph Sanjaya on 2/22/2020.
* Linkedin : https://www.linkedin.com/in/josephsanjaya/
* Github : https://github.com/JosephSanjaya
*/

class HomeFragment : BaseFragment() {
    private val currenciesViewModel: CurrenciesViewModel by viewModel()
    private val countriesViewModel: CountriesViewModel by viewModel()
    private lateinit var binding: HomeLayoutBinding
    private val actionHistories: Int = R.id.action_homeFragment_to_historiesFragment
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
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.ivCountriesFlag.load(R.drawable.ic_idn){
            transformations(CircleCropTransformation())
        }
        setupListener()
        setCurrenciesObserver()
        setCountriesObserver()
        loadCurrencies(Constants.INDONESIA_CURRENCY)
    }

    private fun setCurrenciesObserver()
    {
        if (!currenciesViewModel.currenciesData.hasObservers())
            currenciesViewModel.currenciesData.observe(viewLifecycleOwner, Observer { data ->
            currenciesData = data
        })
        if (!currenciesViewModel.dateCurrenciesData.hasObservers())
            currenciesViewModel.dateCurrenciesData.observe(viewLifecycleOwner, Observer { data ->
            yesterdayCurrenciesData = data
        })
    }

    private fun setCountriesObserver()
    {
        if (!countriesViewModel.countriesData.hasObservers())
            countriesViewModel.countriesData.observe(viewLifecycleOwner, Observer { data ->
            if (data != null) {
                countriesDataList = data
            }
        })
        if (!countriesViewModel.loadingCountries.hasObservers())
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
                        binding.ivCountriesFlag.load(Uri.parse(countriesDataList[position].flag)){
                            transformations(CircleCropTransformation())
                        }
                        selectedType =
                            countriesDataList[position].currencies?.get(1)?.code.toString()
                        loadCurrencies(selectedType)
                        binding.tvSelectedCurrency.text =
                            countriesDataList[position].currencies?.get(1)?.code.toString()
                        binding.tvSelectedState.text =
                            countriesDataList[position].alpha2Code
                    } else {
                        binding.ivCountriesFlag.load(Uri.parse(countriesDataList[position].flag)){
                            transformations(CircleCropTransformation())
                        }
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
        binding.fabConverter.setOnClickListener {
            val dialogFrag: ConverterFragment = ConverterFragment.newInstance()
            dialogFrag.setCountriesData(countriesDataList)
            dialogFrag.setParentFab(binding.fabConverter)
            dialogFrag.show((requireFragmentManager()), dialogFrag.tag)
        }
        binding.fabRate.setOnClickListener {
            val uri =
                Uri.parse("market://details?id=" + mContext?.packageName)
            val goToMarket = Intent(Intent.ACTION_VIEW, uri)
            goToMarket.addFlags(
                Intent.FLAG_ACTIVITY_NO_HISTORY or
                        Intent.FLAG_ACTIVITY_NEW_DOCUMENT or
                        Intent.FLAG_ACTIVITY_MULTIPLE_TASK
            )
            try {
                startActivity(goToMarket)
            } catch (e: ActivityNotFoundException) {
                startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("http://play.google.com/store/apps/details?id=" + mContext?.packageName)
                    )
                )
            }
        }

        getCountries()
        binding.llState.setOnClickListener {
            showRecyclerDialog(
                mContext!!,
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
        countriesViewModel.getCountriesByAlpha(Constants.COUNTRIES_SUPPORTED_CODES)
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
//        currenciesAdapter.setOnItemClickListener { adapter, view, position ->
//            val bundle = Bundle()
//            bundle.putString("base", selectedType)
//            bundle.putString("symbols", currenciesDataList[position].data?.symbols)
//            Functions.navigateTo(this@HomeFragment, actionHistories, bundle)
//        }
    }

    private fun loadCurrencies(type: String) {
        currenciesDataList = ArrayList()
        setupCurrenciesAdapter()
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
                        currenciesDataList.remove(currenciesDataList.find { data -> data.data?.symbols == type ||  data.data?.value == null})
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

    override fun onDetach() {
        super.onDetach()
        if (currenciesViewModel.loadingCurrencyByDate.hasObservers()) currenciesViewModel.loadingCurrencyByDate.removeObservers(
            viewLifecycleOwner
        )
        if (currenciesViewModel.loadingLatestCurrency.hasObservers()) currenciesViewModel.loadingLatestCurrency.removeObservers(
            viewLifecycleOwner
        )
        if (currenciesViewModel.dateCurrenciesData.hasObservers()) currenciesViewModel.dateCurrenciesData.removeObservers(
            viewLifecycleOwner
        )
        if (currenciesViewModel.currenciesData.hasObservers()) currenciesViewModel.currenciesData.removeObservers(
            viewLifecycleOwner
        )
        if (countriesViewModel.loadingCountries.hasObservers()) countriesViewModel.loadingCountries.removeObservers(
            viewLifecycleOwner
        )
        if (countriesViewModel.countriesData.hasObservers()) countriesViewModel.countriesData.removeObservers(
            viewLifecycleOwner
        )
    }
}