package com.tatweer.mhussain.hbmsu_android.services

import com.evernote.android.job.JobCreator
import android.support.annotation.NonNull
import android.support.annotation.Nullable
import com.evernote.android.job.Job



class NoteJobCreater: JobCreator {

    @Nullable
    override fun create(tag: String): Job? {
        when (tag) {
            WeatherUpdateJob.TAG -> return WeatherUpdateJob()
            else -> return null
        }
    }
}