package com.example.data.repository

import com.example.core.error.Failure
import com.example.core.functional.Either
import com.example.data.mapper.CharacterResponseMapper
import com.example.data.mapper.EpisodeResponseMapper
import com.example.data.mapper.LocationResponseMapper
import com.example.data.remote.api.ApiService
import com.example.data.remote.handler.safeApiCall
import com.example.domain.model.CharacterModel
import com.example.domain.model.EpisodeModel
import com.example.domain.model.LocationModel
import com.example.domain.repository.RickAndMortyRepository
import javax.inject.Inject


class DefaultRickAndMortyRepository @Inject constructor(
    private val api: ApiService,
    private val characterMapper: CharacterResponseMapper,
    private val locationMapper: LocationResponseMapper,
    private val episodeMapper: EpisodeResponseMapper,
) : RickAndMortyRepository {
    override suspend fun getCharacters(): Either<Failure, List<CharacterModel>> = safeApiCall(
        apiCall = { api.getCharacters() },
        mapper = { characterMapper.map(it) }
    )

    override suspend fun getEpisodes(): Either<Failure, List<EpisodeModel>> = safeApiCall(
        apiCall = { api.getEpisodes() },
        mapper = { episodeMapper.map(it) }
    )


    override suspend fun getLocations(): Either<Failure, List<LocationModel>> = safeApiCall(
        apiCall = { api.getLocations() },
        mapper = { locationMapper.map(it) }
    )
}
