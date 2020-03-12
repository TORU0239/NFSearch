package sg.toru.nfsearch.domain.usecase

import retrofit2.Response
import sg.toru.nfsearch.data.api.ImageSearchService
import sg.toru.nfsearch.data.entity.ApiResponse
import sg.toru.nfsearch.data.entity.SearchResultWrapper
import javax.inject.Inject


class ImageSearchUseCaseImpl @Inject constructor(
    private val service: ImageSearchService
): BaseNetworkUseCase(), ImageSearchUseCase {
    override suspend fun request(
        query:String,
        pageNumber: Int
    ): ApiResponse<SearchResultWrapper> {
        return baseResponse {
            service.getImageSearch(query(query, pageNumber))
        }
    }

    private fun query(
        query:String,
        pageNumber:Int
    ):HashMap<String, String> {
        val map = HashMap<String,String>()
        map["autoCorrect"] = "true"
        map["safeSearch"] = "false"
        map["q"] = query
        map["pageSize"] = "20"
        map["pageNumber"] = pageNumber.toString()
        return map
    }
}