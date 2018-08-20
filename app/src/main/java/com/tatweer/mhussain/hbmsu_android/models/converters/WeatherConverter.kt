package com.tatweer.mhussain.hbmsu_android.models.converters

import android.arch.persistence.room.TypeConverter
import java.util.Collections.emptyList
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.tatweer.mhussain.hbmsu_android.models.Main
import com.tatweer.mhussain.hbmsu_android.models.Weather
import java.util.*


class WeatherConverter {

    var gson = Gson()

    @TypeConverter
    fun stringToWeatherObjectList(data: String?): List<Weather> {
        if (data == null) {
            return Collections.emptyList()
        }

        val listType = object : TypeToken<List<Weather>>() {

        }.getType()

        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun weatherObjectListToString(someObjects: List<Weather>): String {
        return gson.toJson(someObjects)
    }
}