package com.example.data.mapper

import com.example.core.functional.mapOrDefault
import com.example.core.functional.orDefault
import com.example.core.mapper.ResultMapper
import com.example.data.response.LocationsResponse
import com.example.domain.model.LocationModel
import javax.inject.Inject

class LocationResponseMapper @Inject constructor() :
    ResultMapper<LocationsResponse, List<LocationModel>> {
    override fun map(input: LocationsResponse): List<LocationModel> {
        return input.results.mapOrDefault {
            LocationModel(
                id = it.id.orDefault(),
                name = it.name.orEmpty(),
                type = it.type.orEmpty(),
                dimension = it.dimension.orEmpty(),
                url = it.url.orEmpty(),
            )
        }
    }

}