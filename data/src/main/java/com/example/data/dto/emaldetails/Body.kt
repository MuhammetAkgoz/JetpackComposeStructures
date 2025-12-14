package com.example.data.dto.emaldetails


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Body(
    @SerialName("html")
    val html: String?,
    @SerialName("text")
    val text: String?
)