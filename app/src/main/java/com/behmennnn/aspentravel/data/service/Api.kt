package com.behmennnn.aspentravel.data.service

import com.behmennnn.aspentravel.data.dto.LocationDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface Api {

    @GET("location")
    suspend fun getAllLocations() : Response<List<LocationDTO>>

    @GET("recommended")
    suspend fun getAllRecommended() : Response<List<LocationDTO>>

    @GET("location/{location_id}")
    suspend fun getLocationById(@Path("location_id") locationId: Int) : Response<LocationDTO>
}