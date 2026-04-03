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
    private val cachedCharacters = java.util.concurrent.ConcurrentHashMap<Int, CharacterModel>()

    override suspend fun getCharacters(page: Int): Either<Failure, List<CharacterModel>> {
        val response = safeApiCall(
            apiCall = { api.getCharacters(page) },
            mapper = { characterMapper.map(input = it) }
        )

        if (response is Either.Right) {
            response.value.forEach { character ->
                cachedCharacters[character.id] = character
            }
        }

        return response
    }

    override suspend fun getEpisodes(page: Int): Either<Failure, List<EpisodeModel>> = safeApiCall(
        apiCall = { api.getEpisodes(page) },
        mapper = { episodeMapper.map(it) }
    )


    override suspend fun getLocations(page: Int): Either<Failure, List<LocationModel>> =
        safeApiCall(
            apiCall = { api.getLocations(page) },
            mapper = { locationMapper.map(it) }
        )

    override fun getCachedCharacterById(id: Int): Either<Failure, CharacterModel> {
        val character = cachedCharacters[id]

        return if (character != null) {
            Either.Right(character)
        } else {
            Either.Left(Failure.unspecified())
        }
    }
}
