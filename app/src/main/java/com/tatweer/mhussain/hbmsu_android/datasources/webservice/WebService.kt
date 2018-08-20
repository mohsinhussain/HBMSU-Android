package com.tatweer.mhussain.hbmsu_android.datasources.webservice


import com.tatweer.mhussain.hbmsu_android.BuildConfig
import com.tatweer.mhussain.hbmsu_android.models.WeatherResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface WebService {
    @GET("data/2.5/forecast?")
    fun getWeather(@Query("q") cityName: String = "Dubai", @Query("units") unit: String = "metric", @Query("cnt") count: Int = 30, @Query("APPID") apiKey: String = BuildConfig.APIKEY): Call<WeatherResponse>
}