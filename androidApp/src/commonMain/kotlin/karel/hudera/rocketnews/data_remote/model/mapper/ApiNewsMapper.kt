package karel.hudera.rocketnews.data_remote.model.mapper

import karel.hudera.rocketnews.data_remote.model.apiNews.ApiNews
import karel.hudera.rocketnews.domain.model.News
import karel.hudera.rocketnews.domain.model.map.Mapper

class ApiNewsMapper : Mapper<ApiNews, News>() {
    override fun map(model: ApiNews): News = model.run {
        News(
            date,
            explanation,
            hdurl, // image
            title,
            url // image
        )
    }

    override fun inverseMap(model: News): ApiNews {
        TODO("Not yet implemented")
    }
}