package com.sanjayajoseph.livecurrency.application.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.blankj.utilcode.util.ToastUtils
import com.sanjayajoseph.livecurrency.R
import com.sanjayajoseph.livecurrency.api.models.currencies.base.CurrenciesHistoriesResponse
import com.sanjayajoseph.livecurrency.application.common.Constants
import com.sanjayajoseph.livecurrency.application.common.Functions
import com.sanjayajoseph.livecurrency.application.viewmodel.HistoriesViewModel
import com.sanjayajoseph.livecurrency.base.BaseFragment
import com.sanjayajoseph.livecurrency.databinding.HistoriesLayoutBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber


/*
* @author Joseph Sanjaya on 3/1/2020.
* Linkedin : https://www.linkedin.com/in/josephsanjaya/
* Github : https://github.com/JosephSanjaya
*/
class HistoriesFragment : BaseFragment()
{
    private val historiesViewModel: HistoriesViewModel by viewModel()
    private lateinit var binding: HistoriesLayoutBinding
    private lateinit var base: String
    private lateinit var symbols: String
    private var historiesResponse: CurrenciesHistoriesResponse? = null
    private val actionBack: Int = R.id.action_historiesFragment_to_homeFragment
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<HistoriesLayoutBinding>(
            inflater,
            R.layout.histories_layout, container, false
        ).apply {
        }

        Functions.setupBackButtonHandler(
                Runnable { back() },
        this,
        true
        )
        setupListener()
        getBundles()
        loadHistories()
        return binding.root
    }

    private fun setupListener()
    {
        binding.fabBack.setOnClickListener { back() }
    }

    private fun back() {
        Functions.navigateTo(
            this,
            actionBack
        )
    }

    private fun getBundles()
    {
        try {
            if(arguments != null)
            {
                base = arguments?.getString("base")!!
                symbols = arguments?.getString("symbols")!!
            }
        } catch (e: Exception) {
            Timber.tag(Constants.TAG).e(e)
        }
    }

    private fun loadHistories() {
        if (historiesViewModel.historiesData.hasObservers()) historiesViewModel.historiesData.removeObservers(
            viewLifecycleOwner
        )
        historiesViewModel.historiesData.observe(viewLifecycleOwner, Observer { data ->
            if (data != null) {
                historiesResponse = data
            }
        })
        if (historiesViewModel.loadingHistories.hasObservers()) historiesViewModel.loadingHistories.removeObservers(
            viewLifecycleOwner
        )
        historiesViewModel.loadingHistories.observe(
            viewLifecycleOwner,
            Observer { loadingStatus ->
                when (loadingStatus) {
                    Constants.LOADING_STATUS_LOAD -> return@Observer
                    Constants.LOADING_STATUS_SUCCESS -> binding.tvTest.text = historiesResponse?.toString()
                    Constants.LOADING_STATUS_FAILED -> {
                        ToastUtils.showLong("Failed to Load Data, try Refreshing")
                    }
                    else -> {
                        ToastUtils.showLong("Failed to Load Data, Unknown Error")
                    }
                }
            })
        historiesViewModel.getHistories("2018-01-01", "2018-02-01", base, symbols)
    }

}
