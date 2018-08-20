package com.tatweer.mhussain.hbmsu_android.dagger

import android.arch.lifecycle.ViewModel
import dagger.MapKey
import kotlin.reflect.KClass


@MapKey
annotation class ViewModelKey(val value: KClass<out ViewModel>)