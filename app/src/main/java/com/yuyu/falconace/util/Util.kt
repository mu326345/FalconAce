package com.yuyu.falconace.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import com.yuyu.falconace.FalconApplication


object Util {

//    fun isInternetConnected(): Boolean {
//        val cm = FalconApplication.instance
//            .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
//        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
//        return activeNetwork?.isConnectedOrConnecting == true
//    }

    fun getString(resourceId: Int): String {
        return FalconApplication.instance.getString(resourceId)
    }
}
