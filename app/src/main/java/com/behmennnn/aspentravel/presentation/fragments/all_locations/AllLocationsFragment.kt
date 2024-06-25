package com.behmennnn.aspentravel.presentation.fragments.all_locations

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.behmennnn.aspentravel.R
import com.behmennnn.aspentravel.common.BaseFragment
import com.behmennnn.aspentravel.common.BaseViewModel
import com.behmennnn.aspentravel.common.util.GridSpacingItemDecoration
import com.behmennnn.aspentravel.common.util.Status
import com.behmennnn.aspentravel.databinding.FragmentAllLocationsBinding
import com.behmennnn.aspentravel.presentation.fragments.all_locations.adapter.AllLocationsAdapter
import com.behmennnn.aspentravel.presentation.fragments.home.HomeViewModel

class AllLocationsFragment : BaseFragment<FragmentAllLocationsBinding>(FragmentAllLocationsBinding::inflate) {

    private val allLocationsAdapter by lazy { AllLocationsAdapter() }
    private lateinit var allLocationsViewModel: AllLocationsViewModel
    private lateinit var baseViewModel: BaseViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        allLocationsViewModel = ViewModelProvider(requireActivity())[AllLocationsViewModel::class.java]
        baseViewModel = ViewModelProvider(requireActivity())[BaseViewModel::class.java]

        binding.backButton.setOnClickListener {
            findNavController().navigateUp()
        }

        allLocationsAdapter.setOnItemClickListener {
            baseViewModel.getLocationById(it.id)
            findNavController().navigate(R.id.action_allLocationsFragment_to_locationDetailFragment)
        }

        with(binding.locationRv){
            adapter = allLocationsAdapter
            layoutManager = GridLayoutManager(requireActivity(),2)
            addItemDecoration(GridSpacingItemDecoration(2, 50))
        }
        observeLiveData()

    }

    private fun observeLiveData(){
        allLocationsViewModel.locationData.observe(viewLifecycleOwner, Observer {
            when(it.status){
                Status.SUCCESS -> {
                    allLocationsAdapter.location = it.data!!
                }
                Status.ERROR -> {

                }
                Status.LOADING -> {

                }
            }
        })
    }
}