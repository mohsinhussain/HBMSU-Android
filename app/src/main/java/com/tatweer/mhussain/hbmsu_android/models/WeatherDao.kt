package com.tatweer.mhussain.hbmsu_android.models

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query

@Dao
interface WeatherDao {
    @Query("SELECT * FROM forecast")
    fun queryForecasts(): LiveData<List<Forecast>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertForecast(forecast: Forecast)
}