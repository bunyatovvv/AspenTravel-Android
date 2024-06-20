package com.behmennnn.aspentravel.data.source

import com.behmennnn.aspentravel.common.util.Resource
import com.behmennnn.aspentravel.data.dto.LocationDTO

interface ApiSource {

    suspend fun getAllLocations() : Resource<List<LocationDTO>>

    suspend fun getAllRecommended() : Resource<List<LocationDTO>>

    suspend fun getLocationById( locationId: Int) : Resource<LocationDTO>


}