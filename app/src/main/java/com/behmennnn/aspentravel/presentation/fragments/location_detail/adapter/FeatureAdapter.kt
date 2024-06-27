package com.behmennnn.aspentravel.presentation.fragments.location_detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.behmennnn.aspentravel.common.util.setImageURL
import com.behmennnn.aspentravel.data.dto.Feature
import com.behmennnn.aspentravel.databinding.SingleFeatureBinding

class FeatureAdapter : RecyclerView.Adapter<FeatureAdapter.FeatureHolder>()  {
    class FeatureHolder(val binding: SingleFeatureBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    private val differCallBack = object : DiffUtil.ItemCallback<Feature>() {
        override fun areItemsTheSame(oldItem: Feature, newItem: Feature): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Feature, newItem: Feature): Boolean {
            return oldItem.title == newItem.title
        }
    }

    val differ = AsyncListDiffer(this, differCallBack)
    var feature : List<Feature>
        get() = differ.currentList
        set(value) = differ.submitList(value.toList())

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeatureHolder {
        val binding = SingleFeatureBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return FeatureHolder(binding)
    }

    override fun getItemCount(): Int {
        return feature.size
    }

    override fun onBindViewHolder(holder: FeatureHolder, position: Int) {
        val item = feature[position]
        with(holder.binding){
            featureTitle.text = item.title
            featureIcon.setImageURL(item.icon,holder.itemView.context)
        }
    }
}