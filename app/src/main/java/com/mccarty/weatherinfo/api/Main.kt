package com.mccarty.weatherinfo.api

data class Main(
    val feels_like: Int,
    val grnd_level: Int,
    val humidity: Int,
    val pressure: Int,
    val sea_level: Int,
    val temp: Int,
    val temp_kf: Int,
    val temp_max: Int,
    val temp_min: Int
)