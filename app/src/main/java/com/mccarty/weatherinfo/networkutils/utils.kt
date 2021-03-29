package com.mccarty.weatherinfo.networkutils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities

class utils {
    companion object {
        fun networkIsAvailable(context: Context): Boolean {
            val manager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val network = manager.getNetworkCapabilities(manager.activeNetwork)
            return network != null && network.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
        }
    }
}