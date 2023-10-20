package com.example.rocketnews.data_remote.model.mapper

import co.touchlab.kermit.Logger
import com.example.rocketnews.domain.model.Rocket
import com.example.rocketnews.domain.model.map.Mapper
import com.example.rocketnews.data_remote.model.ApiRocket

class ApiRocketMapper : Mapper<ApiRocket, Rocket>() {
    override fun map(model: ApiRocket): Rocket = model.run {
        Rocket(
            links.patch.small ?: "Failure", // image
            links.patch.large ?: "Failure", // image
            links.webcast ?: "Failure",
            links.youtube_id ?: "Failure",
            links.article ?: "Failure",
            links.wikipedia ?: "Failure",
            static_fire_date_utc ?: "Failure",
            success ?: false,
            date_utc,
            name,
            upcoming,
            id,
        )
    }

    override fun inverseMap(model: Rocket): ApiRocket {
        TODO("Not yet implemented")
    }
}