package com.behmennnn.aspentravel.data.source

import com.behmennnn.aspentravel.common.util.Resource
import com.behmennnn.aspentravel.data.dto.LocationDTO
import com.behmennnn.aspentravel.data.service.Api
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ApiSourceImpl @Inject constructor(private val api: Api) : ApiSource {
    override suspend fun getAllLocations(): Resource<List<LocationDTO>> {
        return withContext(Dispatchers.IO) {
            try {
                Resource.loading(null)
                val response = api.getAllLocations()
                if (response.isSuccessful) {
                    response.body()?.let {
                        Resource.success(it)
                    } ?: Resource.error("null", null)
                } else {
                    val message = "error get all locations"
                    Resource.error(message, null)
                }
            } catch (e: Exception) {
                Resource.error(e.localizedMessage, null)
            }
        }
    }

    override suspend fun getPopular(): Resource<List<LocationDTO>> {
        return withContext(Dispatchers.IO) {
            try {
                Resource.loading(null)
                val response = api.getPopular()
                if (response.isSuccessful) {
                    response.body()?.let {
                        Resource.success(it)
                    } ?: Resource.error("null", null)
                } else {
                    val message = "error get popular locations"
                    Resource.error(message, null)
                }
            } catch (e: Exception) {
                Resource.error(e.localizedMessage, null)
            }
        }
    }

    override suspend fun getAllRecommended(): Resource<List<LocationDTO>> {
        return withContext(Dispatchers.IO) {
            try {
                Resource.loading(null)
                val response = api.getAllRecommended()
                if (response.isSuccessful) {
                    response.body()?.let {
                        Resource.success(it)
                    } ?: Resource.error("null", null)
                } else {
                    val message = "error get all recommendeds"
                    Resource.error(message, null)
                }
            } catch (e: Exception) {
                Resource.error(e.localizedMessage, null)
            }
        }
    }

    override suspend fun getLocationById(locationId: Int): Resource<LocationDTO> {
        return withContext(Dispatchers.IO) {
            try {
                Resource.loading(null)
                val response = api.getLocationById(locationId)
                if (response.isSuccessful) {
                    response.body()?.let {
                        Resource.success(it)
                    } ?: Resource.error("null", null)
                } else {
                    val message = "error get id locations"
                    Resource.error(message, null)
                }
            } catch (e: Exception) {
                Resource.error(e.localizedMessage, null)
            }
        }
    }

    override suspend fun getRecommendedById(recommendedId: Int): Resource<LocationDTO> {
        return withContext(Dispatchers.IO) {
            try {
                Resource.loading(null)
                val response = api.getRecommendedById(recommendedId)
                if (response.isSuccessful) {
                    response.body()?.let {
                        Resource.success(it)
                    } ?: Resource.error("null", null)
                } else {
                    val message = "error get id recommendeds"
                    Resource.error(message, null)
                }
            } catch (e: Exception) {
                Resource.error(e.localizedMessage, null)
            }
        }
    }

    override suspend fun getAllExplore(): Resource<List<LocationDTO>> {
        return withContext(Dispatchers.IO) {
            try {
                Resource.loading(null)
                val response = api.getAllExplore()
                if (response.isSuccessful) {
                    response.body()?.let {
                        Resource.success(it)
                    } ?: Resource.error("null", null)
                } else {
                    val message = "error get all explores"
                    Resource.error(message, null)
                }
            } catch (e: Exception) {
                Resource.error(e.localizedMessage, null)
            }
        }
    }
}