package com.example.data.remote.api

import com.example.data.dto.emaillist.EmailListItemDto
import com.example.data.dto.emaldetails.EmailDetailsDto
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("api/v1/emaillist")
    suspend fun getEmailList(): Response<ArrayList<EmailListItemDto>>

    @GET("api/v1/emaildetails")
    suspend fun getEmailDetail(): Response<ArrayList<EmailDetailsDto>>
}