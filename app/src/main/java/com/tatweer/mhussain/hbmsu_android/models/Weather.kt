package com.tatweer.mhussain.hbmsu_android.models

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

class Weather ( val id: Int, val main: String, val description: String, val icon: String) {
}

