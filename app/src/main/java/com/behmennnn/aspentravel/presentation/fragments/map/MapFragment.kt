package com.behmennnn.aspentravel.presentation.fragments.map

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.behmennnn.aspentravel.R
import com.behmennnn.aspentravel.common.BaseViewModel
import com.behmennnn.aspentravel.common.util.Status
import com.behmennnn.aspentravel.common.util.Utils.Companion.mapFragment
import com.behmennnn.aspentravel.databinding.FragmentMapBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng

class MapFragment : Fragment(), OnMapReadyCallback {

    private var _binding: FragmentMapBinding? = null
    private val binding get() = _binding!!
    private lateinit var map: GoogleMap
    private lateinit var address: LatLng

    private lateinit var baseViewModel: BaseViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMapBinding.inflate(inflater, container, false)
        val view = binding.root
        mapFragment = childFragmentManager.findFragmentById(R.id.map) as? SupportMapFragment
        mapFragment?.getMapAsync(this)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        baseViewModel = ViewModelProvider(requireActivity())[BaseViewModel::class.java]
        observeLiveData()

    }

    private fun observeLiveData(){
        baseViewModel.locationDetail.observe(viewLifecycleOwner, Observer{
            when(it.status){
                Status.SUCCESS -> {
                    val item = it.data!!
                    address = LatLng(item.longitude,item.latitude)
                }
                Status.LOADING -> {

                }
                Status.ERROR -> {

                }
            }
        })
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(address, 15f))
    }
}