package com.behmennnn.aspentravel.data.dto

data class LocationDTO(
    val book: String,
    val city: String,
    val country: String,
    val created_at: Long,
    val description: String,
    val id: Int,
    val image: String,
    val latitude: Double,
    val longitude: Double,
    val name: String,
    val price: Int,
    val rating: Double
)