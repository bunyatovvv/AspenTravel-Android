package com.behmennnn.aspentravel.data.service

import com.behmennnn.aspentravel.data.dto.HotelDTO
import com.behmennnn.aspentravel.data.dto.LocationDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface Api {

    @GET("location")
    suspend fun getAllLocations() : Response<List<LocationDTO>>

    @GET("location_popular")
    suspend fun getPopular() : Response<List<LocationDTO>>

    @GET("recommended")
    suspend fun getAllRecommended() : Response<List<LocationDTO>>

    @GET("explore")
    suspend fun getAllExplore() : Response<List<LocationDTO>>

    @GET("hotel")
    suspend fun getAllHotels() : Response<List<HotelDTO>>

    @GET("location/{location_id}")
    suspend fun getLocationById(@Path("location_id") locationId: Int) : Response<LocationDTO>

    @GET("recommended/{recommended_id}")
    suspend fun getRecommendedById(@Path("recommended_id") recommendedId: Int) : Response<LocationDTO>
}