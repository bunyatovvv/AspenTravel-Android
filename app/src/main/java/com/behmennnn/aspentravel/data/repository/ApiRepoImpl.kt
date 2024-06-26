package com.behmennnn.aspentravel.data.repository

import com.behmennnn.aspentravel.domain.repository.ApiRepo
import com.behmennnn.aspentravel.common.util.Resource
import com.behmennnn.aspentravel.common.util.Status
import com.behmennnn.aspentravel.data.dto.HotelDTO
import com.behmennnn.aspentravel.data.dto.LocationDTO
import com.behmennnn.aspentravel.data.source.ApiSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ApiRepoImpl @Inject constructor(
    private val source: ApiSource
) : ApiRepo {
    override suspend fun getAllLocations(): Flow<Resource<List<LocationDTO>>> = flow {
        emit(Resource.loading(null))
        val response = source.getAllLocations()
        when (response.status) {
            Status.SUCCESS -> {
                emit(Resource.success(response.data))
            }

            Status.ERROR -> {
                emit(Resource.error(response.message ?: "Error", null))
            }

            else -> {
                emit(Resource.loading(emptyList()))
            }
        }
    }

    override suspend fun getAllHotels(): Flow<Resource<List<HotelDTO>>> = flow {
        emit(Resource.loading(null))
        val response = source.getAllHotels()
        when (response.status) {
            Status.SUCCESS -> {
                emit(Resource.success(response.data))
            }

            Status.ERROR -> {
                emit(Resource.error(response.message ?: "Error", null))
            }

            else -> {
                emit(Resource.loading(emptyList()))
            }
        }
    }

    override suspend fun getPopular(): Flow<Resource<List<LocationDTO>>> = flow {
        emit(Resource.loading(null))
        val response = source.getPopular()
        when (response.status) {
            Status.SUCCESS -> {
                emit(Resource.success(response.data))
            }

            Status.ERROR -> {
                emit(Resource.error(response.message ?: "Error", null))
            }

            else -> {
                emit(Resource.loading(emptyList()))
            }
        }
    }

    override suspend fun getAllRecommended(): Flow<Resource<List<LocationDTO>>> = flow {
        emit(Resource.loading(null))
        val response = source.getAllRecommended()
        when (response.status) {
            Status.SUCCESS -> {
                emit(Resource.success(response.data))
            }

            Status.ERROR -> {
                emit(Resource.error(response.message ?: "Error", null))
            }

            else -> {
                emit(Resource.loading(emptyList()))
            }
        }
    }

    override suspend fun getLocationById(locationId: Int): Flow<Resource<LocationDTO>> = flow {
        emit(Resource.loading(null))
        val response = source.getLocationById(locationId)
        when (response.status) {
            Status.SUCCESS -> {
                emit(Resource.success(response.data))
            }

            Status.ERROR -> {
                emit(Resource.error(response.message ?: "Error", null))
            }

            else -> {
                emit(Resource.loading(null))
            }
        }
    }

    override suspend fun getRecommendedById(recommendedId: Int): Flow<Resource<LocationDTO>> = flow {
        emit(Resource.loading(null))
        val response = source.getRecommendedById(recommendedId)
        when (response.status) {
            Status.SUCCESS -> {
                emit(Resource.success(response.data))
            }

            Status.ERROR -> {
                emit(Resource.error(response.message ?: "Error", null))
            }

            else -> {
                emit(Resource.loading(null))
            }
        }
    }

    override suspend fun getAllExplore(): Flow<Resource<List<LocationDTO>>>  = flow {
        emit(Resource.loading(null))
        val response = source.getAllExplore()
        when (response.status) {
            Status.SUCCESS -> {
                emit(Resource.success(response.data))
            }

            Status.ERROR -> {
                emit(Resource.error(response.message ?: "Error", null))
            }

            else -> {
                emit(Resource.loading(emptyList()))
            }
        }
    }
}