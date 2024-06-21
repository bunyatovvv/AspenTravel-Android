package com.behmennnn.aspentravel.presentation.fragments.explore

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
class ExploreViewModel @Inject constructor(
    private var apiRepoImpl: ApiRepoImpl
) : ViewModel() {


    private val _exploreData = MutableLiveData<Resource<List<LocationDTO>>>()
    val exploreData: LiveData<Resource<List<LocationDTO>>> get()=_exploreData

    init {
        getAllExplore()
    }

    fun getAllExplore() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = apiRepoImpl.getAllExplore()
            response.collectLatest {
                _exploreData.postValue(it)
            }
        }
    }
}