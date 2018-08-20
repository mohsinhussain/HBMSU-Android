package com.tatweer.mhussain.hbmsu_android.dagger.components

import com.tatweer.mhussain.hbmsu_android.dagger.WeatherApplication
import com.tatweer.mhussain.hbmsu_android.dagger.modules.AppModule
import com.tatweer.mhussain.hbmsu_android.dagger.modules.BuildersModule
import com.tatweer.mhussain.hbmsu_android.dagger.modules.NetModule
import com.tatweer.mhussain.hbmsu_android.dagger.modules.ViewModelModule
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AndroidInjectionModule::class, BuildersModule::class, ViewModelModule::class, AppModule::class, NetModule::class))
interface AppComponent {
    fun inject(app: WeatherApplication)
}