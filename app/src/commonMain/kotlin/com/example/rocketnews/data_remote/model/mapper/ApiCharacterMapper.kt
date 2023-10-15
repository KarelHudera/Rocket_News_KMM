package com.example.rocketnews.data_remote.model.mapper

import com.example.rocketnews.domain.model.Rocket
import com.example.rocketnews.domain.model.map.Mapper
import com.example.rocketnews.data_remote.model.ApiRocket

class ApiRocketMapper : Mapper<ApiRocket, Rocket>() {
    override fun map(model: ApiRocket): Rocket = model.run {
        Rocket(
            links.patch.small, // image
            links.patch.large, // image
            links.webcast,
            links.youtubeId,
            links.article,
            links.wikipedia,
            staticFireDateUtc,
            success,
            payloads,
            dateUtc,
            name,
            upcoming,
            id,
        )
    }

    override fun inverseMap(model: Rocket): ApiRocket {
        TODO("Not yet implemented")
    }
}