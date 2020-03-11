package sg.toru.nfsearch.domain.usecase

import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response
import sg.toru.nfsearch.data.api.ImageSearchService
import sg.toru.nfsearch.data.entity.ApiResponse
import sg.toru.nfsearch.data.entity.SearchResultWrapper
import sg.toru.nfsearch.domain.usecase.ImageSearchUseCase
import javax.inject.Inject


class ImageSearchUseCaseImpl @Inject constructor(private val service: ImageSearchService): ImageSearchUseCase {
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
        map["pageNumber"] = pageNumber.toString()
        return map
    }

    private suspend fun<T> baseResponse(apiCall:suspend ()-> Response<T>): ApiResponse<T> {
        val response: Response<T>
        try {
            response = apiCall.invoke()
        } catch (exception:Exception){
            exception.printStackTrace()
            // Exception
            return ApiResponse.ApiFailure(exception.message!!)
        }

        return if(response.isSuccessful) {
            // Success
            ApiResponse.ApiSuccess(response.body()!!)
        } else {
            // Failure
            ApiResponse.ApiFailure(response.message())
        }
    }
}