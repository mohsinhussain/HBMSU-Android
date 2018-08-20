package com.tatweer.mhussain.hbmsu_android.views.activities


import android.app.SearchManager
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.PopupMenu
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import com.tatweer.mhussain.hbmsu_android.R
import com.tatweer.mhussain.hbmsu_android.models.Forecast
import com.tatweer.mhussain.hbmsu_android.models.WeatherRepository
import com.tatweer.mhussain.hbmsu_android.services.WeatherUpdateJob
import com.tatweer.mhussain.hbmsu_android.utils.ItemClickSupport
import com.tatweer.mhussain.hbmsu_android.utils.NotificationUtil
import com.tatweer.mhussain.hbmsu_android.utils.ResourceObserver
import com.tatweer.mhussain.hbmsu_android.utils.Utils
import com.tatweer.mhussain.hbmsu_android.viewmodels.ForecastsViewModel
import com.tatweer.mhussain.hbmsu_android.views.uiadapters.ForecastsViewAdapter
import dagger.android.AndroidInjection
import java.util.*
import javax.inject.Inject

class WeatherListingActivity : AbstractActivity() {


    val TAG = "WeatherListingActivity"


    private lateinit var forecastsViewModel: ForecastsViewModel
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    @Inject
    lateinit var utils: Utils
    @Inject
    lateinit var preferences: SharedPreferences
    @Inject
    lateinit var repository: WeatherRepository

    lateinit var progressBar: ProgressBar

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: ForecastsViewAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var forecastsList: ArrayList<Forecast>


    private val mNotificationTime = Calendar.getInstance().timeInMillis + 30000 //Set after 5 seconds from the current time.
    private var mNotified = false

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather_listing)

        supportActionBar!!.setDisplayShowHomeEnabled(true)
//        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        progressBar = findViewById<ProgressBar>(R.id.progressbar) as ProgressBar


        recyclerView = findViewById(R.id.ticketsRecyclerView) as RecyclerView
        recyclerView.setHasFixedSize(true)
        val llm = LinearLayoutManager(this@WeatherListingActivity)
        recyclerView.setLayoutManager(llm)


        forecastsList = ArrayList<Forecast>()


        val editor = preferences.edit()

        if(!preferences.contains("isImperial")){
            editor.putBoolean("isImperial", false)
            editor.commit()
        }


        forecastsViewModel = ViewModelProviders.of(this, viewModelFactory).get(ForecastsViewModel::class.java)

        forecastsViewModel.forecastList.observe(this, ResourceObserver("WeatherListingActivity",
                hideLoading = ::hideLoading,
                showLoading = ::showLoading,
                onSuccess = ::showList,
                onError = ::showErrorMessage))


        if (!forecastsViewModel.initialized)
            forecastsViewModel.cuisineInput.value = 0



        ItemClickSupport.addTo(recyclerView).setOnItemClickListener(object : ItemClickSupport.OnItemClickListener {
            override fun onItemClicked(recyclerView: RecyclerView, position: Int, v: View) {

                val intent = Intent(baseContext, DetailActivity::class.java)

                intent.putExtra("title", forecastsList.get(position).weather.get(0).main)
                intent.putExtra("desc", forecastsList.get(position).weather.get(0).description)
                intent.putExtra("temp", forecastsList.get(position).main.temp)
                intent.putExtra("tempMin", forecastsList.get(position).main.temp_min)
                intent.putExtra("tempMax", forecastsList.get(position).main.temp_max)
                intent.putExtra("humidity", forecastsList.get(position).main.humidity)
                intent.putExtra("air", forecastsList.get(position).main.pressure)
                intent.putExtra("wind", forecastsList.get(position).wind.speed)
                intent.putExtra("icon", forecastsList.get(position).weather.get(0).icon)
                intent.putExtra("clouds", forecastsList.get(position).clouds.all)

                startActivity(intent)
                return

            }
        })


        if (!mNotified) {
            NotificationUtil().setNotification(mNotificationTime, this@WeatherListingActivity)
        }


        WeatherUpdateJob.scheduleJob(repository)



    }



    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.list_menu, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {


        when (item.itemId) {
            R.id.unit_change -> {
                val editor = preferences.edit()

                if(preferences.contains("isImperial")){
                    if(preferences.getBoolean("isImperial",false)){
                        editor.putBoolean("isImperial", false)
                    }
                    else{
                        editor.putBoolean("isImperial", true)
                    }
                }
                else{
                    editor.putBoolean("isImperial", false)
                }

                editor.commit()

                viewAdapter.notifyDataSetChanged()
                return true
            }


        }
        return super.onOptionsItemSelected(item)

    }





    private fun showList(forecasts: List<Forecast>) {

        Toast.makeText(this@WeatherListingActivity, "SUCCESS count "+forecasts.count(), Toast.LENGTH_LONG).show()
        forecastsList = ArrayList(forecasts)
//        viewAdapter.notifyDataSetChanged()
        viewAdapter = ForecastsViewAdapter(this@WeatherListingActivity, forecastsList, preferences)
        recyclerView.setAdapter(viewAdapter)

    }

    private fun showErrorMessage(error: String) {
        Toast.makeText(this@WeatherListingActivity, "Error: $error", Toast.LENGTH_LONG).show()
    }

    private fun showLoading() {
        progressBar.visibility = View.VISIBLE
    }


    private fun hideLoading() {
        progressBar.visibility = View.GONE
    }
}
