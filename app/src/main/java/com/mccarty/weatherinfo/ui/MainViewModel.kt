package com.mccarty.weatherinfo.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    var navigateToWeatherDetails = MutableLiveData<Boolean>()

    fun callNavToDetails(nav: Boolean) {
        navigateToWeatherDetails.value = nav
    }
}