package com.sanjayajoseph.livecurrency.application.base

import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.sanjayajoseph.livecurrency.R
import com.sanjayajoseph.livecurrency.api.models.countries.CountriesResponse
import com.sanjayajoseph.livecurrency.api.models.currencies.entities.CurrenciesEntity
import com.sanjayajoseph.livecurrency.application.ui.adapters.CurrenciesAdapter
import kotlinx.android.synthetic.main.view_dialog_recycler.view.*

/*
* @author Joseph Sanjaya on 2/22/2020.
* Linkedin : https://www.linkedin.com/in/josephsanjaya/
* Github : https://github.com/JosephSanjaya
*/


open class BaseFragment : Fragment() {

}