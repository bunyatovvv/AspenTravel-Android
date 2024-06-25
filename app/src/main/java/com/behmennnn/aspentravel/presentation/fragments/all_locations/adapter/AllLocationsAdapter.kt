package com.behmennnn.aspentravel.presentation.fragments.all_locations.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.behmennnn.aspentravel.common.util.setImageURL
import com.behmennnn.aspentravel.data.dto.LocationDTO
import com.behmennnn.aspentravel.databinding.SingleAllLocationsBinding

class AllLocationsAdapter : RecyclerView.Adapter<AllLocationsAdapter.AllLocationsHolder>()  {
    class AllLocationsHolder(val binding: SingleAllLocationsBinding) : RecyclerView.ViewHolder(binding.root) {

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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllLocationsHolder {
        val binding = SingleAllLocationsBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return AllLocationsHolder(binding)
    }

    override fun getItemCount(): Int {
        return location.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: AllLocationsHolder, position: Int) {
        val item = location[position]
        with(holder.binding){
            titleText.text = item.name
            countryText.text = "${item.country}, ${item.city}"
            locationImage.setImageURL(item.image,holder.itemView.context)
        }
        holder.itemView.setOnClickListener {
            onItemClickListener?.let {
                it(item)
            }
        }
    }

    private var onItemClickListener: ((LocationDTO) -> Unit)? = null

    fun setOnItemClickListener(listener: (LocationDTO) -> Unit) {
        onItemClickListener = listener
    }
}