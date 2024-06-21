package com.behmennnn.aspentravel.presentation.fragments.explore

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.behmennnn.aspentravel.common.util.setImageURL
import com.behmennnn.aspentravel.data.dto.LocationDTO
import com.behmennnn.aspentravel.databinding.ViewpagerSingleBinding

class ViewPagerAdapter () : RecyclerView.Adapter<ViewPagerAdapter.PagerViewHolder>() {
    inner class PagerViewHolder(val binding : ViewpagerSingleBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    private val differCallBack = object : DiffUtil.ItemCallback<LocationDTO>() {
        override fun areItemsTheSame(oldItem: LocationDTO, newItem: LocationDTO): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: LocationDTO, newItem: LocationDTO): Boolean {
            return oldItem.id == newItem.id
        }
    }

    val differ = AsyncListDiffer(this, differCallBack)
    var location : List<LocationDTO>
        get() = differ.currentList
        set(value) = differ.submitList(value.toList())

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerViewHolder {
        val view = ViewpagerSingleBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return PagerViewHolder(view)
    }

    override fun getItemCount(): Int {
        return location.size
    }

    override fun onBindViewHolder(holder: PagerViewHolder, position: Int) {
        val item = location[position]
        with(holder.binding){
            descriptionText.text = item.description
            titleText.text = item.name
            locationText.text = "${item.city}, ${item.country}"
            priceText.text = "$${item.price}"
            bgImage.setImageURL(item.image,holder.itemView.context)
        }

    }
}