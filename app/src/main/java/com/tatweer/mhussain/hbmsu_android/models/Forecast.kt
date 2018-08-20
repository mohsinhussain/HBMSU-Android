package com.tatweer.mhussain.hbmsu_android.models

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
class Forecast (@PrimaryKey val dt: Int, val main: Main, val weather: List<Weather>, val cloud: Cloud, val wind: Wind, val dt_txt: String) {
}

