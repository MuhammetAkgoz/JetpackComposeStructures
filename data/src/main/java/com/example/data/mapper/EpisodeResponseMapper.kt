package com.example.data.mapper

import com.example.core.functional.mapOrDefault
import com.example.core.functional.orDefault
import com.example.core.mapper.ResultMapper
import com.example.data.response.EpisodesResponse
import com.example.domain.model.EpisodeModel
import javax.inject.Inject


class EpisodeResponseMapper @Inject constructor() :
    ResultMapper<EpisodesResponse, List<EpisodeModel>> {
    override fun map(input: EpisodesResponse): List<EpisodeModel> {
        return input.results.mapOrDefault {
            EpisodeModel(
                id = it?.id.orDefault(),
                name = it?.name.orEmpty(),
                airDate = it?.airDate.orEmpty(),
                episode = it?.episode.orEmpty(),
                url = it?.url.orEmpty(),
                created = it?.created.orEmpty()
            )
        }
    }
}