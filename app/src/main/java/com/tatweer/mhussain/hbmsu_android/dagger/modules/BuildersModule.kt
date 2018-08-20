package com.tatweer.mhussain.hbmsu_android.dagger.modules

import com.tatweer.mhussain.hbmsu_android.views.WeatherListingActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class BuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeWeatherListingActivity(): WeatherListingActivity
}