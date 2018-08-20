package com.tatweer.mhussain.hbmsu_android.models.converters

import android.arch.persistence.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class Converters {

    @TypeConverter
    fun fromString(value: String): ArrayList<String> {
        val listType = object : TypeToken<ArrayList<String>>() {

        }.getType()
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromArrayLisr(list: ArrayList<String>): String {
        val gson = Gson()
        return gson.toJson(list)
    }
}