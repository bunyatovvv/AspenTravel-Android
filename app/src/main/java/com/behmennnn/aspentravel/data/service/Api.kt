package com.behmennnn.aspentravel.data.service

import com.behmennnn.aspentravel.data.dto.LocationDTO
import retrofit2.Response
import retrofit2.http.GET

interface Api {

    @GET("location")
    suspend fun getAllLocations() : Response<List<LocationDTO>>

    @GET("recommended")
    suspend fun getAllRecommended() : Response<List<LocationDTO>>
}