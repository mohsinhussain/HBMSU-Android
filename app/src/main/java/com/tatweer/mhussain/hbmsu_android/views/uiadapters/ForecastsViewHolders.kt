package com.tatweer.mhussain.hbmsu_android.views.uiadapters

import android.graphics.Color
import android.media.Image
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.TextView

import com.tatweer.mhussain.hbmsu_android.R
import android.support.v4.graphics.drawable.DrawableCompat
import android.graphics.drawable.Drawable





class ForecastsViewHolders(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var titleTextView: TextView
    var dayTextView: TextView
    var tempTextView: TextView
    var iconImageView: ImageView

    init {
        titleTextView = itemView.findViewById<View>(R.id.titleTextView) as TextView
        dayTextView = itemView.findViewById<View>(R.id.dayTextView) as TextView
        tempTextView = itemView.findViewById<View>(R.id.tempTextView) as TextView
        iconImageView = itemView.findViewById<View>(R.id.iconImageView) as ImageView

    }

}
