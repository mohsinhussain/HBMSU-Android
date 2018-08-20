package com.tatweer.mhussain.hbmsu_android.datasources.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.tatweer.mhussain.hbmsu_android.models.*

/**
 * Created by romantolmachev on 22/11/2017.
 */
@Database(entities = arrayOf(Forecast::class, Weather::class, Cloud::class, Wind::class), version = 1)
abstract class Database : RoomDatabase() {
    abstract fun weatherDao(): WeatherDao
}