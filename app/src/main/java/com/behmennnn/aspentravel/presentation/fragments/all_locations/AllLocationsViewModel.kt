package com.behmennnn.aspentravel.presentation.fragments.all_locations

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
class AllLocationsViewModel @Inject constructor(
    private val apiRepoImpl: ApiRepoImpl
) : ViewModel() {

    private val _locationsData : MutableLiveData<Resource<List<LocationDTO>>> = MutableLiveData()
    val locationData : LiveData<Resource<List<LocationDTO>>> get() = _locationsData

    init {
        getAllLocations()
    }

    fun getAllLocations() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = apiRepoImpl.getAllLocations()
            response.collectLatest {
                _locationsData.postValue(it)
            }
        }
    }

}