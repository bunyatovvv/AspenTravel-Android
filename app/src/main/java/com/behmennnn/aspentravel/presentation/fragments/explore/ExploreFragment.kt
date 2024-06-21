package com.behmennnn.aspentravel.presentation.fragments.explore

import android.icu.text.Transliterator.Position
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.viewpager2.widget.ViewPager2
import com.behmennnn.aspentravel.R
import com.behmennnn.aspentravel.common.BaseFragment
import com.behmennnn.aspentravel.common.util.setImageURL
import com.behmennnn.aspentravel.databinding.FragmentExploreBinding
import com.behmennnn.aspentravel.domain.model.ViewPagerModel
import com.behmennnn.aspentravel.presentation.fragments.location_detail.ViewPagerAdapter

class ExploreFragment : BaseFragment<FragmentExploreBinding>(FragmentExploreBinding::inflate) {


    private val params = LinearLayout.LayoutParams(
        LinearLayout.LayoutParams.WRAP_CONTENT,
        LinearLayout.LayoutParams.WRAP_CONTENT
    ).apply {
        setMargins(8,0,8,0)
    }
    private lateinit var pageChangeListener: ViewPager2.OnPageChangeCallback

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val item1 = ViewPagerModel("salam", "uzun sozun qisasi bunu test edirem", "https://images.squarespace-cdn.com/content/v1/54dbbc3ce4b0a4fe55c89d18/1527593435141-J2R0AV1YGT40XLWMLYSI/htl-2815+%281%29.jpg?format=1000w")
        val item2 = ViewPagerModel("salasdasdam", "uzun sozun qisasi bunu test easdasddirem", "https://i.natgeofe.com/n/f01fa8fc-d26a-4da1-8704-c73b789aabbd/santorini_AWL_GRE1919AW_HR.jpg?w=1280&h=853")
        val item3 = ViewPagerModel("salasdasdam", "uzun sozun qisasi bunu test easdasddireasdasm", "https://i.natgeofe.com/n/f01fa8fc-d26a-4da1-8704-c73b789aabbd/santorini_AWL_GRE1919AW_HR.jpg?w=1280&h=853")
        val item4 = ViewPagerModel("salasdasdam", "uzun sozun qisasi bunu test easdasddireasdasm", "https://i.natgeofe.com/n/f01fa8fc-d26a-4da1-8704-c73b789aabbd/santorini_AWL_GRE1919AW_HR.jpg?w=1280&h=853")


        val items = listOf<ViewPagerModel>(item1,item2,item3,item4)

        val adapter = ViewPagerAdapter(items)
        binding.viewPager.adapter = adapter


        val dotsImage = Array(items.size) {ImageView(requireActivity())}

        dotsImage.forEach {
            it.setImageResource(R.drawable.vp_notselected)
            binding.dotIndicator.addView(it,params)
        }
        dotsImage[0].setImageResource(R.drawable.vp_selected)
        pageChangeListener = object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {

                dotsImage.mapIndexed{index, imageview->

                    if (position == index){
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

    override fun onDestroyView() {
        super.onDestroyView()
        binding.viewPager.unregisterOnPageChangeCallback(pageChangeListener)
    }

    private fun createDotIndicator(count: Int){
        for (i in 0 until count){
            val dot = ImageView(requireContext())
            dot.setImageResource(R.drawable.vp_selector)
            binding.dotIndicator.addView(dot)
        }
    }

    private fun updateDotIndicator(position: Int){
        for (i in 0 until binding.dotIndicator.childCount){
            val dot = binding.dotIndicator.getChildAt(i) as ImageView
            dot.isSelected = 1 == position
        }

    }
}