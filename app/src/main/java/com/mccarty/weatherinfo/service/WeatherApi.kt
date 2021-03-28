package com.mccarty.weatherinfo.api

import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET(WeatherConstants.BASE_URL)
    suspend fun searchWeather(
        @Query(WeatherConstants.QUERY) city: String,
        @Query(WeatherConstants.APPID) appid: String,
        @Query(WeatherConstants.UNITS) units: String): String
}