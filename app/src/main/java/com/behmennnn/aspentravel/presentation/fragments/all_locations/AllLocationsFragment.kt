package com.behmennnn.aspentravel.presentation.fragments.all_locations

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.behmennnn.aspentravel.common.BaseFragment
import com.behmennnn.aspentravel.databinding.FragmentAllLocationsBinding
import com.behmennnn.aspentravel.presentation.fragments.all_locations.adapter.AllLocationsAdapter
import com.behmennnn.aspentravel.presentation.fragments.home.HomeViewModel

class AllLocationsFragment : BaseFragment<FragmentAllLocationsBinding>(FragmentAllLocationsBinding::inflate) {

    private val allLocationsAdapter by lazy { AllLocationsAdapter() }
    private lateinit var allLocationsViewModel: AllLocationsViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        allLocationsViewModel = ViewModelProvider(requireActivity())[AllLocationsViewModel::class.java]

        binding.arrowBack.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun observeLiveData(){

    }
}