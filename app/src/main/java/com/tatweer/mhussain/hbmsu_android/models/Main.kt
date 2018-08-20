package com.tatweer.mhussain.hbmsu_android.models

import android.arch.persistence.room.Entity

@Entity
class Main (val temp: Int, val temp_min: Int, val temp_max: Int, val pressure: Int, val sea_level: Int, val grnd_level: Int, val humidity: Int, val temp_kf: Int) {
}

