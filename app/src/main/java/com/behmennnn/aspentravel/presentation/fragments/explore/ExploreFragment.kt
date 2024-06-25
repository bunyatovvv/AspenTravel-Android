package com.behmennnn.aspentravel.presentation.fragments.explore

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.behmennnn.aspentravel.R
import com.behmennnn.aspentravel.common.BaseFragment
import com.behmennnn.aspentravel.common.util.Status
import com.behmennnn.aspentravel.databinding.FragmentExploreBinding
import com.behmennnn.aspentravel.presentation.fragments.explore.adapter.ViewPagerAdapter

class ExploreFragment : BaseFragment<FragmentExploreBinding>(FragmentExploreBinding::inflate) {

    private lateinit var exploreViewModel: ExploreViewModel
    private val exploreAdapter by lazy { ViewPagerAdapter() }
    private lateinit var pageChangeListener: ViewPager2.OnPageChangeCallback

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        exploreViewModel = ViewModelProvider(requireActivity())[ExploreViewModel::class.java]
        binding.viewPager.adapter = exploreAdapter
        observeLiveData()
        exploreAdapter.setOnBookingClickListener {
            val openURL = Intent(Intent.ACTION_VIEW)
            openURL.data = Uri.parse(it.book)
            startActivity(openURL)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.viewPager.unregisterOnPageChangeCallback(pageChangeListener)
    }

    private fun setViewAdapter() {
        val dotsImage = Array(exploreAdapter.location.size) { ImageView(requireActivity()) }

        val params = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        ).apply {
            setMargins(8, 0, 8, 0)
        }

        dotsImage.forEach {
            it.setImageResource(R.drawable.vp_notselected)
            binding.dotIndicator.addView(it, params)
        }
        dotsImage[0].setImageResource(R.drawable.vp_selected)
        pageChangeListener = object : ViewPager2.OnPageChangeCallback() {

            override fun onPageSelected(position: Int) {
                dotsImage.mapIndexed { index, imageview ->
                    if (position == index) {
                        imageview.setImageResource(R.drawable.vp_selected)
                    } else {
                        imageview.setImageResource(R.drawable.vp_notselected)
                    }
                }
                super.onPageSelected(position)
            }
        }
        binding.viewPager.registerOnPageChangeCallback(pageChangeListener)
    }

    private fun observeLiveData() {
        exploreViewModel.exploreData.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    exploreAdapter.location = it.data!!
                    setViewAdapter()
                }

                Status.ERROR -> {

                }

                Status.LOADING -> {

                }
            }
        })
    }
}