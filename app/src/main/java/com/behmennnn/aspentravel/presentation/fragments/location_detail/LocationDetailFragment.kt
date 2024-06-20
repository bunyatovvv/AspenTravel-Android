package com.behmennnn.aspentravel.presentation.fragments.location_detail

import android.annotation.SuppressLint
import android.os.Bundle
import android.provider.ContactsContract.RawContacts.Data
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.behmennnn.aspentravel.common.BaseFragment
import com.behmennnn.aspentravel.common.util.Status
import com.behmennnn.aspentravel.common.util.gone
import com.behmennnn.aspentravel.common.util.setImageURL
import com.behmennnn.aspentravel.common.util.visible
import com.behmennnn.aspentravel.databinding.FragmentLocationDetailBinding
import com.behmennnn.aspentravel.presentation.fragments.home.HomeViewModel

class LocationDetailFragment : BaseFragment<FragmentLocationDetailBinding>(FragmentLocationDetailBinding::inflate) {

    private lateinit var homeViewModel: HomeViewModel


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeViewModel = ViewModelProvider(requireActivity())[HomeViewModel::class.java]
        observeLiveData()
        binding.backButton.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun observeLiveData(){
        homeViewModel.locationDetail.observe(viewLifecycleOwner, Observer{
            when(it.status){
                Status.SUCCESS -> {
                    with(it.data!!){
                        binding.locationTitle.text = this.name
                        binding.descriptionText.text = this.description
                        binding.priceText.text = "$${this.price}"
                        binding.locationImage.setImageURL(this.image,requireActivity())
                        binding.ratingText.text = this.rating.toString()
                        binding.mainScrollView.visible()

                    }
                }
                Status.ERROR -> {
                    binding.mainScrollView.gone()

                }
                Status.LOADING -> {
                    binding.mainScrollView.gone()
                }
            }
        })
    }
}