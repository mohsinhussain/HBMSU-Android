package com.tatweer.mhussain.hbmsu_android.views


import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import com.tatweer.mhussain.hbmsu_android.R
import com.tatweer.mhussain.hbmsu_android.models.Forecast
import com.tatweer.mhussain.hbmsu_android.utils.ResourceObserver
import com.tatweer.mhussain.hbmsu_android.utils.Utils
import com.tatweer.mhussain.hbmsu_android.viewmodels.ForecastsViewModel
import dagger.android.AndroidInjection
import javax.inject.Inject

class WeatherListingActivity : AbstractActivity() {


    val TAG = "WeatherListingActivity"


    private lateinit var forecastsViewModel: ForecastsViewModel
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    @Inject
    lateinit var utils: Utils

    lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather_listing)

        progressBar = findViewById<ProgressBar>(R.id.progressbar) as ProgressBar

        forecastsViewModel = ViewModelProviders.of(this, viewModelFactory).get(ForecastsViewModel::class.java)

        forecastsViewModel.forecastList.observe(this, ResourceObserver("WeatherListingActivity",
                hideLoading = ::hideLoading,
                showLoading = ::showLoading,
                onSuccess = ::showMarkers,
                onError = ::showErrorMessage))


        if (!forecastsViewModel.initialized)
            forecastsViewModel.cuisineInput.value = 0

    }


    private fun showMarkers(forecasts: List<Forecast>) {

        Toast.makeText(this@WeatherListingActivity, "SUCCESS count "+forecasts.count(), Toast.LENGTH_LONG).show()

    }

    private fun showErrorMessage(error: String) {
        Toast.makeText(this@WeatherListingActivity, "Error: $error", Toast.LENGTH_LONG).show()
    }

    private fun showLoading() {
        progressBar.visibility = View.VISIBLE
    }


    private fun hideLoading() {
        progressBar.visibility = View.GONE
    }
}
