package com.tatweer.mhussain.hbmsu_android.views


import android.os.Bundle
import com.tatweer.mhussain.hbmsu_android.R
import dagger.android.AndroidInjection

class WeatherListingActivity : AbstractActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        setContentView(R.layout.activity_weather_listing)
    }
}
