package com.example.domain.repository

import com.example.core.error.Failure
import com.example.core.functional.Either
import com.example.domain.model.CharacterModel
import com.example.domain.model.EpisodeModel
import com.example.domain.model.LocationModel


interface RickAndMortyRepository{
    suspend fun getCharacters(page: Int): Either<Failure, List<CharacterModel>>
    suspend fun getEpisodes(page: Int): Either<Failure, List<EpisodeModel>>
    suspend fun getLocations(page: Int): Either<Failure, List<LocationModel>>
}