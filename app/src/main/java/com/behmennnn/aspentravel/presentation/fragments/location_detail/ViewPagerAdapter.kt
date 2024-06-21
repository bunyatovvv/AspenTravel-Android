package com.behmennnn.aspentravel.presentation.fragments.location_detail

import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.behmennnn.aspentravel.R
import com.behmennnn.aspentravel.common.util.setImageURL
import com.behmennnn.aspentravel.domain.model.ViewPagerModel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlin.concurrent.timerTask

class ViewPagerAdapter (private val items: List<ViewPagerModel>) : RecyclerView.Adapter<ViewPagerAdapter.PagerViewHolder>() {
    inner class PagerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.viewpager_single, parent, false)
        return PagerViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: PagerViewHolder, position: Int) {
        val item = items[position]
        val descriptionText = holder.itemView.findViewById<TextView>(R.id.descriptionText)
        val image = holder.itemView.findViewById<ImageView>(R.id.bgImage)

        descriptionText.text = item.des
        image.setImageURL(item.image,holder.itemView.context)

    }
}