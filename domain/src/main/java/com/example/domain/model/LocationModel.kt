package com.example.domain.model


data class LocationModel(
    val id: Int? = null,
    val name: String,
    val type: String? = null,
    val dimension: String? = null,
    val url: String,
    val residents: List<String>? = null,
)