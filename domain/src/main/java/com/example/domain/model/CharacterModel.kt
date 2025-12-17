package com.example.domain.model

data class CharacterModel(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val location: LocationModel,
    val origin: OriginModel,
    val image: String,
)

data class OriginModel(
    val name: String,
    val url: String
)