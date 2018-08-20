package com.tatweer.mhussain.hbmsu_android.datasources.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import com.tatweer.mhussain.hbmsu_android.models.*
import com.tatweer.mhussain.hbmsu_android.models.converters.*

/**
 * Created by romantolmachev on 22/11/2017.
 */
@Database(entities = arrayOf(Forecast::class), version = 1)
@TypeConverters(MainConverter::class, WeatherConverter::class, WindConverter::class, CloudConverter::class)
abstract class Database : RoomDatabase() {
    abstract fun weatherDao(): WeatherDao
}