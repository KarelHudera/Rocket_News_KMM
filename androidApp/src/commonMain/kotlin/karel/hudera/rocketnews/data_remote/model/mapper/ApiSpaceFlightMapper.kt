package karel.hudera.rocketnews.data_remote.model.mapper

import karel.hudera.rocketnews.data_remote.model.apiSpaceFlight.Result
import karel.hudera.rocketnews.domain.model.SpaceFlightNews
import karel.hudera.rocketnews.domain.model.map.Mapper

class ApiSpaceFlightMapper : Mapper<Result, SpaceFlightNews>() {
    override fun map(model: Result): SpaceFlightNews = model.run {
        SpaceFlightNews(
            id,
            image_url,
            news_site,
            published_at,
            summary,
            title,
            updated_at,
            url
        )
    }

    override fun inverseMap(model: SpaceFlightNews): Result {
        TODO("Not yet implemented")
    }
}