package com.behmennnn.aspentravel.presentation.fragments.message

import android.os.Bundle
import android.os.Looper
import android.view.View
import com.behmennnn.aspentravel.R
import com.behmennnn.aspentravel.common.BaseFragment
import com.behmennnn.aspentravel.common.util.animateCharacterByCharacter
import com.behmennnn.aspentravel.common.util.gone
import com.behmennnn.aspentravel.common.util.visible
import com.behmennnn.aspentravel.databinding.FragmentMessageBinding

class MessageFragment : BaseFragment<FragmentMessageBinding>(FragmentMessageBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val languages = resources.getStringArray(R.array.countries)
        val welcomeText = resources.getString(R.string.welcome_travel_planner)


//        val spinner = binding.spinner2
//        val adapter = ArrayAdapter(
//            requireActivity(),
//            android.R.layout.simple_spinner_item, languages
//        )
//        spinner.adapter = adapter
        binding.nextButton.gone()

        binding.welcome.animateCharacterByCharacter(resources.getString(R.string.welcome_travel_planner),60L)

        android.os.Handler(Looper.getMainLooper()).postDelayed({
                binding.nextButton.visible()
        }, welcomeText.length * 60L)

//        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
//            override fun onItemSelected(
//                parent: AdapterView<*>?,
//                view: View?,
//                position: Int,
//                id: Long
//            ) {
//                when(position){
//                    1 -> binding.editTextText.gone()
//                    2 -> binding.editTextText.gone()
//                    3 -> binding.editTextText.visible()
//                    4 -> binding.editTextText.gone()
//                }
//            }
//
//            override fun onNothingSelected(parent: AdapterView<*>?) {
//                TODO("Not yet implemented")
//            }
//        }
    }
}