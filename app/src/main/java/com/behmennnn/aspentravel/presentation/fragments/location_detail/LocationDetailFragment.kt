package com.behmennnn.aspentravel.presentation.fragments.location_detail

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract.RawContacts.Data
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.behmennnn.aspentravel.R
import com.behmennnn.aspentravel.common.BaseFragment
import com.behmennnn.aspentravel.common.BaseViewModel
import com.behmennnn.aspentravel.common.util.Status
import com.behmennnn.aspentravel.common.util.gone
import com.behmennnn.aspentravel.common.util.setImageURL
import com.behmennnn.aspentravel.common.util.visible
import com.behmennnn.aspentravel.databinding.FragmentLocationDetailBinding
import com.behmennnn.aspentravel.presentation.fragments.home.HomeViewModel

class LocationDetailFragment : BaseFragment<FragmentLocationDetailBinding>(FragmentLocationDetailBinding::inflate) {

    private lateinit var baseViewModel: BaseViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        baseViewModel = ViewModelProvider(requireActivity())[BaseViewModel::class.java]

        observeLiveData()
        binding.backButton.setOnClickListener {
            findNavController().navigateUp()
        }
        binding.showMap.setOnClickListener {
            findNavController().navigate(R.id.action_locationDetailFragment_to_mapFragment)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun observeLiveData(){
        baseViewModel.locationDetail.observe(viewLifecycleOwner, Observer{
            when(it.status){
                Status.SUCCESS -> {
                    with(it.data!!){
                        binding.locationTitle.text = this.name
                        binding.descriptionText.text = this.description
                        binding.priceText.text = "$${this.price}"
                        binding.locationImage.setImageURL(this.image,requireActivity())
                        binding.ratingText.text = this.rating.toString()
                        binding.mainScrollView.visible()
                        binding.bookLayout.visible()
                        binding.bookButton.setOnClickListener {
                            val openURL = Intent(Intent.ACTION_VIEW)
                            openURL.data = Uri.parse(this.book)
                            startActivity(openURL)
                        }
                    }
                }
                Status.ERROR -> {
                    binding.mainScrollView.gone()
                    binding.bookLayout.gone()
                }
                Status.LOADING -> {
                    binding.mainScrollView.gone()
                    binding.bookLayout.gone()

                }
            }
        })
    }
}