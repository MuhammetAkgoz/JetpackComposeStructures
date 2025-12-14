package com.example.data.dto.emaldetails


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SenderInfo(
    @SerialName("email")
    val email: String,
    @SerialName("name")
    val name: String,
    @SerialName("profileImage")
    val profileImage: String?
)