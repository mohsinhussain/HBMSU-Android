package com.tatweer.mhussain.hbmsu_android.dagger.modules

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.tatweer.mhussain.hbmsu_android.dagger.ViewModelKey
import com.tatweer.mhussain.hbmsu_android.viewmodels.ForecastsViewModel
import com.tatweer.mhussain.hbmsu_android.viewmodels.ViewModelFactory
import dagger.Binds

import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Created by romantolmachev on 23/11/2017.
 */
@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(ForecastsViewModel::class)
    internal abstract fun bindRestaurantsViewModel(forecastsViewModel: ForecastsViewModel): ViewModel

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}