package com.behmennnn.aspentravel.presentation.fragments.home.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.behmennnn.aspentravel.common.util.setImageURL
import com.behmennnn.aspentravel.data.dto.LocationDTO
import com.behmennnn.aspentravel.databinding.SinglePopularBinding
import com.behmennnn.aspentravel.databinding.SingleRecommendedBinding

class RecommendedAdapter : RecyclerView.Adapter<RecommendedAdapter.RecommendedHolder>()  {
    class RecommendedHolder(val binding: SingleRecommendedBinding) : RecyclerView.ViewHolder(binding.root) {

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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecommendedHolder {
        val binding = SingleRecommendedBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return RecommendedHolder(binding)
    }

    override fun getItemCount(): Int {
        return location.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: RecommendedHolder, position: Int) {
        val item = location[position]
        with(holder.binding){
            locationTitle.text = item.name
            countryText.text = "${item.country}, ${item.city}"
            locationImage.setImageURL(item.image,holder.itemView.context)
        }
    }
}