package com.behmennnn.aspentravel.presentation.fragments.home

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.behmennnn.aspentravel.common.BaseFragment
import com.behmennnn.aspentravel.common.util.Status
import com.behmennnn.aspentravel.databinding.FragmentHomeBinding
import com.behmennnn.aspentravel.presentation.fragments.home.adapter.PopularAdapter
import com.behmennnn.aspentravel.presentation.fragments.home.adapter.RecommendedAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    private lateinit var homeViewModel: HomeViewModel
    private val popularAdapter by lazy { PopularAdapter() }
    private val recommendedAdapter by lazy { RecommendedAdapter() }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeViewModel = ViewModelProvider(requireActivity())[HomeViewModel::class.java]

        binding.popularRv.adapter = popularAdapter
        binding.recommendedRv.adapter = recommendedAdapter
        binding.popularRv.layoutManager = LinearLayoutManager(requireActivity(),LinearLayoutManager.HORIZONTAL,false)
        binding.recommendedRv.layoutManager = LinearLayoutManager(requireActivity(),LinearLayoutManager.HORIZONTAL,false)

        observeLiveData()
    }

    private fun observeLiveData(){
        homeViewModel.popularData.observe(viewLifecycleOwner, Observer {
            when(it.status){
                Status.SUCCESS -> {
                    popularAdapter.location = it.data!!
                }
                Status.ERROR -> {

                }
                Status.LOADING -> {

                }
            }
        })

        homeViewModel.recommendedData.observe(viewLifecycleOwner, Observer {
            when(it.status){
                Status.SUCCESS -> {
                    recommendedAdapter.location = it.data!!
                }
                Status.ERROR -> {

                }
                Status.LOADING -> {

                }
            }
        })
    }
}