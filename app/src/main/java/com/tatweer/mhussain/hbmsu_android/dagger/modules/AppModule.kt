package com.tatweer.mhussain.hbmsu_android.dagger.modules

import android.app.Application
import android.arch.persistence.room.Room
import android.content.Context
import android.content.SharedPreferences
import com.tatweer.mhussain.hbmsu_android.datasources.database.Database
import com.tatweer.mhussain.hbmsu_android.models.WeatherDao
import com.tatweer.mhussain.hbmsu_android.utils.Utils
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val app: Application) {

    @Provides
    @Singleton
    fun provideApplication(): Application = app

    @Provides
    @Singleton
    fun provideContext(): Context = app.applicationContext

    @Provides
    @Singleton
    fun providesUtils(): Utils = Utils(app)

    @Provides
    @Singleton
    fun provideRestaurantsDatabase(app: Application): Database =
            Room.databaseBuilder(app, Database::class.java, "weather_db").build()

    @Provides
    @Singleton
    fun provideRestaurantsDatabaseideRestaurantsDao(db: Database): WeatherDao = db.weatherDao()


    @Provides
    @Singleton
    fun providePreferences(ctx: Context): SharedPreferences {
        val preferences = ctx.getSharedPreferences("WeatherPrefs", Context.MODE_PRIVATE)
        return preferences
    }

}