package com.tatweer.mhussain.hbmsu_android.models

import android.arch.lifecycle.LiveData
import android.util.Log
import com.tatweer.mhussain.hbmsu_android.datasources.database.NetworkBoundResource
import com.tatweer.mhussain.hbmsu_android.datasources.webservice.Resource
import com.tatweer.mhussain.hbmsu_android.datasources.webservice.WebService
import com.tatweer.mhussain.hbmsu_android.utils.AppExecutors
import com.tatweer.mhussain.hbmsu_android.utils.Utils
import retrofit2.Call
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
open class WeatherRepository @Inject constructor(val webService: WebService, val weatherDao: WeatherDao, val utils: Utils, val appExecutors: AppExecutors) {

    val TAG: String = "WeatherRepository"


    open fun getForecasts(cuisine: Int = 0): LiveData<Resource<List<Forecast>>> {
        return object : NetworkBoundResource<List<Forecast>>(appExecutors) {

            override fun saveNetworkCallResult(data: WeatherResponse?) {
                Log.d(TAG, "saveNetworkCallResult")
                val forecast: List<Forecast> = data!!.list
                forecast?.filterNot {
                    it.dt_txt.isNullOrBlank() or
                            (it.main == null) or
                            (it.weather == null)
                }?.forEach { weatherDao.insertForecast(it) }
            }

            override fun shouldLoadFromNetwork(data: List<Forecast>?): Boolean {
                val shouldLoadFromNetwork = utils.hasConnection() && (data == null || data.isEmpty())
                Log.d(TAG, "shouldLoadFromNetwork: $shouldLoadFromNetwork")
                return shouldLoadFromNetwork
            }

            override fun loadFromDatabase(): LiveData<List<Forecast>> {
                Log.d(TAG, "loadFromDatabase")
//                return when (cuisine == 0) {
//                    true -> weatherDao.queryForecasts()
//                    else -> weatherDao.queryRestaurantsByCuisine(cuisine)
//                }
                return weatherDao.queryForecasts()
            }

            override fun createNetworkCall(): Call<WeatherResponse> {
                Log.d(TAG, "createNetworkCall")
                return webService.getWeather()
            }
        }.asLiveData()
    }
}