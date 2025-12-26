package com.example.domain.model

data class CharacterModel(
    val id: Int,
    val name: String,
    val status: Status,
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

enum class Status(val value: String) {
    Alive("Alive"),
    Dead("Dead"),
    Unknown("unknown");

    companion object {
        fun fromString(targetValue: String): Status {
            return entries.find { it.value == targetValue } ?: Unknown
        }
    }
}