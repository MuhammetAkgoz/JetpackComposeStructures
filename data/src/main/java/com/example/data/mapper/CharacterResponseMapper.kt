package com.example.data.mapper

import com.example.core.functional.mapOrDefault
import com.example.core.functional.orDefault
import com.example.core.mapper.ResultMapper
import com.example.data.response.CharactersResponse
import com.example.domain.model.CharacterModel
import com.example.domain.model.LocationModel
import com.example.domain.model.OriginModel
import javax.inject.Inject


class CharacterResponseMapper @Inject constructor() :
    ResultMapper<CharactersResponse, List<CharacterModel>> {
    override fun map(input: CharactersResponse): List<CharacterModel> {
        return input.results.mapOrDefault {
            CharacterModel(
                id = it?.id.orDefault(),
                name = it?.name.orEmpty(),
                status = it?.status.orEmpty(),
                species = it?.species.orEmpty(),
                type = it?.type.orEmpty(),
                gender = it?.gender.orEmpty(),
                location = it?.location.toLocationModel(),
                origin = it?.origin.toOriginModel(),
                image = it?.image ?: "https://picsum.photos/200",
            )
        }
    }


    private fun CharactersResponse.Result.Location?.toLocationModel(): LocationModel {
        return LocationModel(
            name = this?.name.orEmpty(),
            url = this?.url.orEmpty()
        )
    }

    private fun CharactersResponse.Result.Origin?.toOriginModel(): OriginModel {
        return OriginModel(
            name = this?.name.orEmpty(),
            url = this?.url.orEmpty()
        )
    }
}

