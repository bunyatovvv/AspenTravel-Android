package com.behmennnn.aspentravel.domain.repository

import com.behmennnn.aspentravel.common.util.Resource
import com.behmennnn.aspentravel.data.dto.HotelDTO
import com.behmennnn.aspentravel.data.dto.LocationDTO
import kotlinx.coroutines.flow.Flow

interface ApiRepo {

    suspend fun getAllLocations() : Flow<Resource<List<LocationDTO>>>

    suspend fun getPopular() : Flow<Resource<List<LocationDTO>>>

    suspend fun getAllRecommended() : Flow<Resource<List<LocationDTO>>>

    suspend fun getAllHotels() : Flow<Resource<List<HotelDTO>>>

    suspend fun getAllExplore() : Flow<Resource<List<LocationDTO>>>

    suspend fun getLocationById(locationId: Int) : Flow<Resource<LocationDTO>>

    suspend fun getRecommendedById(recommendedId: Int) : Flow<Resource<LocationDTO>>

}