package com.example.data.dto.emaldetails


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RecipientInfo(
    @SerialName("email")
    val email: String,
    @SerialName("name")
    val name: String
)
