package com.tatweer.mhussain.hbmsu_android.viewmodels

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import com.tatweer.mhussain.hbmsu_android.datasources.webservice.Resource
import com.tatweer.mhussain.hbmsu_android.models.Forecast
import com.tatweer.mhussain.hbmsu_android.models.WeatherRepository
import javax.inject.Inject


class ForecastsViewModel @Inject constructor(private val repository: WeatherRepository) : ViewModel() {

    var initialized = false

    var cuisineInput: MutableLiveData<Int> = MutableLiveData()

    val forecastList: LiveData<Resource<List<Forecast>>> = Transformations.switchMap(cuisineInput) { it ->
        initialized = true; repository.getForecasts()
    }

}