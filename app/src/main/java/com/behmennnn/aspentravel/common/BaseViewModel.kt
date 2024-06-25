package com.behmennnn.aspentravel.common

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
class BaseViewModel @Inject constructor(
    private val apiRepoImpl: ApiRepoImpl
) : ViewModel() {

    private val _locationDetail = MutableLiveData<Resource<LocationDTO>>()
    val locationDetail: LiveData<Resource<LocationDTO>> get()=_locationDetail

    fun getLocationById(locationId: Int){
        viewModelScope.launch(Dispatchers.IO) {
            val response = apiRepoImpl.getLocationById(locationId)
            response.collectLatest {
                _locationDetail.postValue(it)
            }
        }
    }
}