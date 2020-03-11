package sg.toru.nfsearch.domain.usecase

import sg.toru.nfsearch.data.entity.ApiResponse
import sg.toru.nfsearch.data.entity.SearchResultWrapper

interface ImageSearchUseCase {
    suspend fun request(
        query:String,
        pageNumber:Int
    ): ApiResponse<SearchResultWrapper>
}