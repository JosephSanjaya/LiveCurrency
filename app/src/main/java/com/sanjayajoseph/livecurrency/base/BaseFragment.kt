package com.sanjayajoseph.livecurrency.base

import android.app.Activity
import android.content.Context
import androidx.fragment.app.Fragment

/*
* @author Joseph Sanjaya on 2/22/2020.
* Linkedin : https://www.linkedin.com/in/josephsanjaya/
* Github : https://github.com/JosephSanjaya
*/


open class BaseFragment : Fragment() {
    var mContext: Context? = null
    var mActivity: Activity? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
        mActivity = context as Activity
    }

    override fun onDetach() {
        super.onDetach()
        mContext = null
        mActivity = null
    }
}