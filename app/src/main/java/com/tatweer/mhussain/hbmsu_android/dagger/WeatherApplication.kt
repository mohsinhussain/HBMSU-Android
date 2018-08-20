package com.tatweer.mhussain.hbmsu_android.dagger

import android.app.Activity
import android.app.Application
import android.content.Context
import com.squareup.leakcanary.LeakCanary
import com.squareup.leakcanary.RefWatcher
import com.tatweer.mhussain.hbmsu_android.BuildConfig
import com.tatweer.mhussain.hbmsu_android.dagger.modules.AppModule
import com.tatweer.mhussain.hbmsu_android.dagger.modules.NetModule
import com.tatweer.mhussain.hbmsu_android.dagger.components.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject


class WeatherApplication : Application(), HasActivityInjector {

    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    lateinit var refWatcher: RefWatcher

    companion object {
        @JvmStatic fun refWatcher(context: Context): RefWatcher =
                (context.applicationContext as WeatherApplication).refWatcher
    }

     override fun onCreate() {
        super.onCreate()

         if (LeakCanary.isInAnalyzerProcess(this)) {
             // This process is dedicated to LeakCanary for heap analysis.

             return
         }
         refWatcher = LeakCanary.install(this)

         DaggerAppComponent
                 .builder()
                 .appModule(AppModule(this))
                 .netModule(NetModule(BuildConfig.URL))
                 .build()
                 .inject(this)

    }

    override fun activityInjector(): AndroidInjector<Activity> = activityInjector
}