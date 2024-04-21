package karel.hudera.rocketnews.data_remote.model.mapper

import karel.hudera.rocketnews.domain.model.Rocket
import karel.hudera.rocketnews.domain.model.map.Mapper
import karel.hudera.rocketnews.data_remote.model.apiRocket.ApiRocket

class ApiRocketMapper : Mapper<ApiRocket, Rocket>() {
    override fun map(model: ApiRocket): Rocket = model.run {
        Rocket(
            links.patch.small ?: "", // image
            links.patch.large ?: "", // image
            links.reddit.launch ?: "",
            links.flickr.original,
            links.webcast ?: "",
            links.youtube_id ?: "",
            links.article ?: "",
            links.wikipedia ?: "",
            static_fire_date_utc ?: "",
            success ?: false,
            details ?: "No details for this mission",
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