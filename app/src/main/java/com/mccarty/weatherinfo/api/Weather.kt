package com.mccarty.weatherinfo.api

data class Weather(
    val description: String,
    val icon: String,
    val id: Int,
    val main: String
)