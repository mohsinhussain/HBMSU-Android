package com.tatweer.mhussain.hbmsu_android.dagger.modules

import com.tatweer.mhussain.hbmsu_android.views.activities.DetailActivity
import com.tatweer.mhussain.hbmsu_android.views.activities.WeatherListingActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class BuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeWeatherListingActivity(): WeatherListingActivity


    @ContributesAndroidInjector
    abstract fun contributeDetailActivity(): DetailActivity
}