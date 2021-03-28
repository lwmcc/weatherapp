package com.mccarty.weatherinfo.service

import com.mccarty.weatherinfo.api.WeatherConstants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import javax.inject.Inject
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class WeatherService @Inject constructor() {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
                .baseUrl(WeatherConstants.API_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .client(OkHttpClient().newBuilder().build())
                .build()
}