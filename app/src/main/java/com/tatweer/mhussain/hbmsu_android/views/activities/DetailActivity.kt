package com.tatweer.mhussain.hbmsu_android.views.activities

import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.tatweer.mhussain.hbmsu_android.BuildConfig
import com.tatweer.mhussain.hbmsu_android.R
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_detail.*
import javax.inject.Inject

class DetailActivity : AbstractActivity() {


    @Inject
    lateinit var preferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        setContentView(R.layout.activity_detail)

        titleTextView.setText(intent.getStringExtra("title"))
        descTextView.setText(intent.getStringExtra("desc"))
        tempTextView.setText(showTemp(intent.getDoubleExtra("temp", 0.0)))
        minTempTextView.setText(showTemp(intent.getDoubleExtra("tempMin", 0.0)))
        maxTempTextView.setText(showTemp(intent.getDoubleExtra("tempMax", 0.0)))
        humidityTextView.setText(intent.getIntExtra("humidity", 0).toString()+ " %")
        airTextView.setText(intent.getDoubleExtra("air", 0.0).toString())
        windTextView.setText(intent.getDoubleExtra("wind", 0.0).toString())
        cloudsTextView.setText(intent.getIntExtra("clouds", 0).toString()+ " %")


        Glide.with(this@DetailActivity)
                .load(BuildConfig.iconURL+intent.getStringExtra("icon")+".png")
                .into(iconImageView)

//        descTextView.setText(intent.getStringExtra("icon"))




        /**
         * title", forecastsList.get(position).weather.get(0).main)
        intent.putExtra("desc", forecastsList.get(position).weather.get(0).description)
        intent.putExtra("temp", forecastsList.get(position).main.temp)
        intent.putExtra("tempMin", forecastsList.get(position).main.temp_min)
        intent.putExtra("tempMax", forecastsList.get(position).main.temp_max)
        intent.putExtra("humidity", forecastsList.get(position).main.humidity)
        intent.putExtra("air", forecastsList.get(position).main.pressure)
        intent.putExtra("wind"
         */


    }


    fun showTemp(temp: Double): String{
        val fahrenheitTemperature = temp * 9 / 5 + 32
        if(preferences.getBoolean("isImperial", false)){
            return fahrenheitTemperature.toString()+" F"
        }
        else{
            return temp.toString()+" C"
        }
    }
}
