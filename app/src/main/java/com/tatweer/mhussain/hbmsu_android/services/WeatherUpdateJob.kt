package com.tatweer.mhussain.hbmsu_android.services

import com.evernote.android.job.JobRequest
import com.evernote.android.job.JobManager
import com.evernote.android.job.Job.Params
import android.support.annotation.NonNull
import com.evernote.android.job.Job
import com.tatweer.mhussain.hbmsu_android.models.WeatherRepository
import java.util.concurrent.TimeUnit
import javax.inject.Inject


class WeatherUpdateJob : Job() {

    companion object {
        val TAG = "weather_app_update"


        private lateinit var repository: WeatherRepository

        fun scheduleJob(mRepository: WeatherRepository) {
            repository = mRepository
            val jobRequests = JobManager.instance().getAllJobRequestsForTag(WeatherUpdateJob.TAG)
            if (!jobRequests.isEmpty()) {
                return
            }
            JobRequest.Builder(WeatherUpdateJob.TAG)
                    .setPeriodic(TimeUnit.MINUTES.toMillis(15), TimeUnit.MINUTES.toMillis(7))
                    .setUpdateCurrent(true)
                    .setRequiredNetworkType(JobRequest.NetworkType.CONNECTED)
                    .setRequirementsEnforced(true)
                    .build()
                    .schedule()
        }
    }

    override fun onRunJob(params: Params): Result {
        repository.getForecasts()
        return Result.SUCCESS
    }


}