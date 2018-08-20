package com.tatweer.mhussain.hbmsu_android.views.uiadapters

import android.annotation.TargetApi
import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.tatweer.mhussain.hbmsu_android.BuildConfig

import com.tatweer.mhussain.hbmsu_android.R
import com.tatweer.mhussain.hbmsu_android.models.Forecast
import com.tatweer.mhussain.hbmsu_android.utils.Utils
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*
import javax.inject.Inject



class ForecastsViewAdapter(private val context: Context, private val itemList: List<Forecast>, val preferences: SharedPreferences) : RecyclerView.Adapter<ForecastsViewHolders>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastsViewHolders {

        val layoutView = LayoutInflater.from(parent.context).inflate(R.layout.forecast_item_view, null)
        return ForecastsViewHolders(layoutView)
    }

    override fun onBindViewHolder(holder: ForecastsViewHolders, position: Int) {

        val item = itemList[position]

        holder.titleTextView.setText(item.weather.get(0).main)
        holder.tempTextView.setText(showTemp(item.main.temp))
        holder.dayTextView.setText(item.dt_txt)


        Glide.with(context)
                .load(BuildConfig.iconURL+item.weather.get(0).icon+".png")
                .into(holder.iconImageView)


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

    override fun getItemCount(): Int {
        return this.itemList.size
    }
}
