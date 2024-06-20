package com.behmennnn.aspentravel.presentation.fragments.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.behmennnn.aspentravel.common.util.Resource
import com.behmennnn.aspentravel.data.dto.LocationDTO
import com.behmennnn.aspentravel.data.repository.ApiRepoImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val apiRepo: ApiRepoImpl
) : ViewModel() {

    private val _popularData = MutableLiveData<Resource<List<LocationDTO>>>()
    val popularData: LiveData<Resource<List<LocationDTO>>> get()=_popularData

    private val _recommendedData = MutableLiveData<Resource<List<LocationDTO>>>()
    val recommendedData: LiveData<Resource<List<LocationDTO>>> get()=_recommendedData

    private val _locationDetail = MutableLiveData<Resource<LocationDTO>>()
    val locationDetail: LiveData<Resource<LocationDTO>> get()=_locationDetail

    init {
        getAllLocations()
        getAllRecommended()
    }

    fun getAllLocations(){
        viewModelScope.launch(Dispatchers.IO) {
            val response = apiRepo.getAllLocations()
            response.collectLatest {
                _popularData.postValue(it)
            }
        }
    }

    fun getAllRecommended(){
        viewModelScope.launch(Dispatchers.IO) {
            val response = apiRepo.getAllRecommended()
            response.collectLatest {
                _recommendedData.postValue(it)
            }
        }
    }

    fun getLocationById(locationId: Int){
        viewModelScope.launch(Dispatchers.IO) {
            val response = apiRepo.getLocationById(locationId)
            response.collectLatest {
                _locationDetail.postValue(it)
            }
        }
    }
}