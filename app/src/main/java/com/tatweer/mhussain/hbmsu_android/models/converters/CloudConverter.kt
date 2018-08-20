package com.tatweer.mhussain.hbmsu_android.models.converters

import android.arch.persistence.room.TypeConverter
import java.util.Collections.emptyList
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.tatweer.mhussain.hbmsu_android.models.Cloud
import com.tatweer.mhussain.hbmsu_android.models.Main
import java.util.*


class CloudConverter {

    var gson = Gson()

    @TypeConverter
    fun stringToMainObject(data: String?): Cloud? {
        if (data == null) {
            return null
        }

        val typeOfObject = object : TypeToken<Cloud>() {

        }.getType()

        return gson.fromJson(data, typeOfObject)
    }

    @TypeConverter
    fun mainObjectToString(someObjects: Cloud): String {
        return gson.toJson(someObjects)
    }
}