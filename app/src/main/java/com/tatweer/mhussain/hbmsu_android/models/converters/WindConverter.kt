package com.tatweer.mhussain.hbmsu_android.models.converters

import android.arch.persistence.room.TypeConverter
import java.util.Collections.emptyList
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.tatweer.mhussain.hbmsu_android.models.Main
import com.tatweer.mhussain.hbmsu_android.models.Wind
import java.util.*


class WindConverter {

    var gson = Gson()

    @TypeConverter
    fun stringToMainObject(data: String?): Wind? {
        if (data == null) {
            return null
        }

        val typeOfObject = object : TypeToken<Wind>() {

        }.getType()

        return gson.fromJson(data, typeOfObject)
    }

    @TypeConverter
    fun mainObjectToString(someObjects: Wind): String {
        return gson.toJson(someObjects)
    }
}