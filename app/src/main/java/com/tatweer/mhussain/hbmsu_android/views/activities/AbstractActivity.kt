package com.tatweer.mhussain.hbmsu_android.views.activities

import android.support.v7.app.AppCompatActivity
import com.tatweer.mhussain.hbmsu_android.dagger.WeatherApplication

abstract class AbstractActivity : AppCompatActivity() {

    override fun onDestroy() {
        super.onDestroy()
        val refWatcher = WeatherApplication.refWatcher(this)
        refWatcher.watch(this)
    }
}