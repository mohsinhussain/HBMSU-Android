package com.tatweer.mhussain.hbmsu_android.models

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
class Weather (@PrimaryKey val id: Int, val main: String, val description: String, val icon: String) {
}

