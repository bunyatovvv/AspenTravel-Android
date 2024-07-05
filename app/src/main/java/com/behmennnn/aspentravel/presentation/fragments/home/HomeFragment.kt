package com.behmennnn.aspentravel.presentation.fragments.home

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.behmennnn.aspentravel.R
import com.behmennnn.aspentravel.common.BaseFragment
import com.behmennnn.aspentravel.common.BaseViewModel
import com.behmennnn.aspentravel.common.util.Status
import com.behmennnn.aspentravel.common.util.gone
import com.behmennnn.aspentravel.common.util.visible
import com.behmennnn.aspentravel.databinding.FragmentHomeBinding
import com.behmennnn.aspentravel.presentation.fragments.home.adapter.PopularAdapter
import com.behmennnn.aspentravel.presentation.fragments.home.adapter.RecommendedAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var baseViewModel: BaseViewModel
    private val popularAdapter by lazy { PopularAdapter() }
    private val recommendedAdapter by lazy { RecommendedAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeViewModel = ViewModelProvider(requireActivity())[HomeViewModel::class.java]
        baseViewModel = ViewModelProvider(requireActivity())[BaseViewModel::class.java]

        binding.popularRv.adapter = popularAdapter
        binding.recommendedRv.adapter = recommendedAdapter
        binding.popularRv.layoutManager =
            LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
        binding.recommendedRv.layoutManager =
            LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)

        observeLiveData()

        popularAdapter.setOnItemClickListener {
            baseViewModel.getLocationById(it.id)
            findNavController().navigate(R.id.action_homeFragment_to_locationDetailFragment)
        }
        recommendedAdapter.setOnItemClickListener {
            baseViewModel.getRecommendedById(it.id)
            findNavController().navigate(R.id.action_homeFragment_to_locationDetailFragment)
        }

        binding.seeAll.setOnClickListener{
            findNavController().navigate(R.id.action_homeFragment_to_allLocationsFragment)
        }
        refreshLayout()
    }

    private fun refreshLayout(){
        with(binding.swipeRefresh){
            setOnRefreshListener {
                homeViewModel.getPopular()
            }
        }
    }

    private fun observeLiveData() {
        homeViewModel.popularData.observe(viewLifecycleOwner) { location ->
            when (location.status) {
                Status.SUCCESS -> {
                    popularAdapter.location = location.data!!
                    binding.mainScrollView.visible()
                    binding.swipeRefresh.isRefreshing = false
                }
                Status.ERROR -> {
                    binding.mainScrollView.gone()
                }
                Status.LOADING -> {
                    binding.mainScrollView.gone()
                    binding.swipeRefresh.isRefreshing = true
                }
            }
        }

        homeViewModel.recommendedData.observe(viewLifecycleOwner) {
            when (it.status) {
                Status.SUCCESS -> {
                    recommendedAdapter.location = it.data!!
                }
                Status.ERROR -> {
                }
                Status.LOADING -> {
                }
            }
        }
    }
}