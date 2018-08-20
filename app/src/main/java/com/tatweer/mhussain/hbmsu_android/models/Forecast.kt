package com.tatweer.mhussain.hbmsu_android.models

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.arch.persistence.room.TypeConverters
import com.tatweer.mhussain.hbmsu_android.models.converters.Converters
import com.tatweer.mhussain.hbmsu_android.models.converters.MainConverter
import com.tatweer.mhussain.hbmsu_android.models.converters.WeatherConverter

@Entity
class Forecast (@PrimaryKey val dt: Int, val main: Main, @TypeConverters(WeatherConverter::class) val weather: List<Weather>, val clouds: Cloud, val wind: Wind, val dt_txt: String) {
}

