package com.example.data.remote.api

import com.example.data.response.CharactersResponse
import com.example.data.response.EpisodesResponse
import com.example.data.response.LocationsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("character")
    suspend fun getCharacters(@Query("page") page: Int) : Response<CharactersResponse>

    @GET("location")
    suspend fun getLocations(@Query("page") page: Int): Response<LocationsResponse>

    @GET("episode")
    suspend fun getEpisodes(@Query("page") page: Int) : Response<EpisodesResponse>
}