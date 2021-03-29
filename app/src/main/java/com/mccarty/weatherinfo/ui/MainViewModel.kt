package com.mccarty.weatherinfo.ui

import android.content.Context
import android.net.ConnectivityManager
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.*
import com.mccarty.weatherinfo.api.*
import com.mccarty.weatherinfo.service.WeatherService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.ArrayList

class MainViewModel @ViewModelInject constructor(private val weatherService: WeatherService) : ViewModel() {

    var userCity = MutableLiveData<String>()

    var navigateToWeatherDetails = MutableLiveData<Boolean>()
    var weatherResponseArrayList = MutableLiveData<ArrayList<WeatherResponse>>()
    var weatherResponseObject = MutableLiveData<WeatherResponse>()

    fun callNavToDetails(nav: Boolean) {
        navigateToWeatherDetails.value = nav
    }

    fun setWeatherResponseObject(response: WeatherResponse) {
        weatherResponseObject.value = response
    }

    fun setUserCity(city: String) {
        userCity.value = city
    }
    fun getWeatherData(city: String) {
        val service: WeatherApi = weatherService.provideRetrofit().create(WeatherApi::class.java)

        viewModelScope.launch(Dispatchers.IO) {
            val call: String = service.searchWeather(city, WeatherConstants.API_KEY, WeatherConstants.UNITS)
            val parser = JsonParser()
            val obj: JsonObject = parser.parse(call) as JsonObject

            val cod: JsonPrimitive = obj.get("cod") as JsonPrimitive

            if(cod.asInt == WeatherConstants.STATUS_OK) {
                val weatherResponseList = ArrayList<WeatherResponse>()

                val jsonArray: JsonArray = obj.get("list") as JsonArray

                jsonArray.forEach { element ->
                    val wr: WeatherResponse = Gson().fromJson(element, WeatherResponse::class.java)
                    weatherResponseList.add(wr)
                }

                weatherResponseArrayList.postValue(weatherResponseList)
            }
        }
    }

}