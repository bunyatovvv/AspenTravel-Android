package com.behmennnn.aspentravel.presentation.fragments.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.behmennnn.aspentravel.common.util.setImageURL
import com.behmennnn.aspentravel.data.dto.LocationDTO
import com.behmennnn.aspentravel.databinding.SinglePopularBinding

class PopularAdapter : RecyclerView.Adapter<PopularAdapter.PopularHolder>()  {
    class PopularHolder(val binding: SinglePopularBinding) : RecyclerView.ViewHolder(binding.root) {

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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularHolder {
        val binding = SinglePopularBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return PopularHolder(binding)
    }

    override fun getItemCount(): Int {
        return location.size
    }

    override fun onBindViewHolder(holder: PopularHolder, position: Int) {
        val item = location[position]
        with(holder.binding){
            locationTitle.text = item.name
            locationRating.text = item.rating.toString()
            locationImage.setImageURL(item.image,holder.itemView.context)
        }
    }
}