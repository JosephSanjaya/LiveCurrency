package com.sanjayajoseph.livecurrency.application.ui.fragments

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.text.TextUtils
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import coil.api.load
import coil.transform.CircleCropTransformation
import com.allattentionhere.fabulousfilter.AAH_FabulousFragment
import com.blankj.utilcode.util.ToastUtils
import com.chad.library.adapter.base.BaseQuickAdapter
import com.sanjayajoseph.livecurrency.R
import com.sanjayajoseph.livecurrency.api.models.countries.CountriesResponse
import com.sanjayajoseph.livecurrency.application.common.Constants
import com.sanjayajoseph.livecurrency.application.ui.adapters.CountriesAdapter
import com.sanjayajoseph.livecurrency.application.viewmodel.ConverterViewModel
import kotlinx.android.synthetic.main.converter_layout.view.*
import kotlinx.android.synthetic.main.view_dialog_recycler.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.NumberFormat

/*
* @author Joseph Sanjaya on 2/29/2020.
* Linkedin : https://www.linkedin.com/in/josephsanjaya/
* Github : https://github.com/JosephSanjaya
*/

class ConverterFragment : AAH_FabulousFragment() {
    private lateinit var contentView: View
    private var convertedResult: Double? = null
    private val converterViewModel: ConverterViewModel by viewModel()
    private val nf: NumberFormat = NumberFormat.getInstance()
    private var countriesDataList: ArrayList<CountriesResponse> = ArrayList()
    private var mRecyclerDialog: AlertDialog? = null
    private var mCurrency: String = Constants.INDONESIA_CURRENCY
    private var mTarget: String = Constants.US_CURRENCY

    companion object {
        fun newInstance(): ConverterFragment {
            return ConverterFragment()
        }
    }

    fun setCountriesData(data: ArrayList<CountriesResponse>) {
        countriesDataList = data
    }

    override fun setupDialog(dialog: Dialog, style: Int) {
        contentView = View.inflate(context, R.layout.converter_layout, null)
        setupParameter()
        setupListener()
        setObserver()
        super.setupDialog(dialog, style) //call super at last
    }

    private fun setObserver() {
        if (!converterViewModel.convertedData.hasObservers())
            converterViewModel.convertedData.observe(requireActivity(), Observer { data ->
                if (data != null) {
                    convertedResult = data
                }
            })
        if (!converterViewModel.loadingConvert.hasObservers())
            converterViewModel.loadingConvert.observe(
                requireActivity(),
                Observer { loadingStatus ->
                    when (loadingStatus) {
                        Constants.LOADING_STATUS_LOAD -> return@Observer
                        Constants.LOADING_STATUS_SUCCESS -> contentView.etTarget.setText(
                            nf.format(
                                convertedResult
                            )
                        )
                        Constants.LOADING_STATUS_FAILED -> ToastUtils.showLong("Failed to Convert Data, try again")
                        else -> ToastUtils.showLong("Failed to Convert Data, Unknown Error")
                    }
                })
    }


    private fun setupParameter() {
        contentView.ivCountriesFlag.load(R.drawable.ic_idn) {
            transformations(CircleCropTransformation())
        }
        contentView.ivTargetCountriesFlag.load(R.drawable.ic_usa) {
            transformations(CircleCropTransformation())
        }
        nf.maximumFractionDigits = 6
        nf.isGroupingUsed = false
        setAnimationDuration(300) //optional; default 500ms
        setPeekHeight(500) // optional; default 400dp
        setViewMain(contentView.rl_content) //necessary; main bottomsheet view
        setMainContentView(contentView) // necessary; call at end before super
    }

    private fun showRecyclerDialog(
        mContext: Context,
        title: String,
        isTarget: Boolean,
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
                    contentView.etCurrency.setText("")
                    contentView.etTarget.setText("")
                    if (mRecyclerDialog != null && mRecyclerDialog!!.isShowing)
                        mRecyclerDialog!!.dismiss()
                    if (countriesDataList[position].name == "Singapore") {
                        if (isTarget) {
                            contentView.ivTargetCountriesFlag.load(countriesDataList[position].flag) {
                                transformations(CircleCropTransformation())
                            }
                            mTarget =
                                countriesDataList[position].currencies?.get(1)
                                    ?.code.toString()
                            contentView.tvTarget.text = mTarget
                        } else {
                            contentView.ivCountriesFlag.load(countriesDataList[position].flag) {
                                transformations(CircleCropTransformation())
                            }
                            mCurrency =
                                countriesDataList[position].currencies?.get(1)
                                    ?.code.toString()
                            contentView.tvCurrency.text = mCurrency
                        }
                    } else {
                        if (isTarget) {
                            contentView.ivTargetCountriesFlag.load(countriesDataList[position].flag) {
                                transformations(CircleCropTransformation())
                            }
                            mTarget =
                                countriesDataList[position].currencies?.get(0)
                                    ?.code.toString()
                            contentView.tvTarget.text = mTarget
                        } else {
                            contentView.ivCountriesFlag.load(countriesDataList[position].flag) {
                                transformations(CircleCropTransformation())
                            }
                            mCurrency =
                                countriesDataList[position].currencies?.get(0)
                                    ?.code.toString()
                            contentView.tvCurrency.text = mCurrency
                        }
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
        contentView.ivCountriesFlag.setOnClickListener {
            showRecyclerDialog(
                requireContext(),
                "Countries",
                false,
                CountriesAdapter(R.layout.view_countries_list, countriesDataList)
            )
        }
        contentView.ivTargetCountriesFlag.setOnClickListener {
            showRecyclerDialog(
                requireContext(),
                "Countries",
                true,
                CountriesAdapter(R.layout.view_countries_list, countriesDataList)
            )
        }
        contentView.fabClose.setOnClickListener {
            closeFilter("closed")
            if (converterViewModel.convertedData.hasObservers()) converterViewModel.convertedData.removeObservers(
                requireActivity()
            )
            if (converterViewModel.loadingConvert.hasObservers()) converterViewModel.loadingConvert.removeObservers(
                requireActivity()
            )
        }
        contentView.btnConvert.setOnClickListener {
            if (TextUtils.isEmpty(contentView.etCurrency.valueString)) ToastUtils.showLong("Valid values is required")
            else {
                converterViewModel.convertRate(
                    contentView.etCurrency.valueString.toDouble(),
                    mCurrency,
                    mTarget
                )
            }
        }
    }

    override fun onCancel(dialog: DialogInterface) {
        super.onCancel(dialog)
        closeFilter("closed")
        if (converterViewModel.convertedData.hasObservers()) converterViewModel.convertedData.removeObservers(
            requireActivity()
        )
        if (converterViewModel.loadingConvert.hasObservers()) converterViewModel.loadingConvert.removeObservers(
            requireActivity()
        )
    }
}