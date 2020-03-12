package sg.toru.nfsearch.domain.usecase

import retrofit2.Response
import sg.toru.nfsearch.data.entity.ApiResponse

open class BaseNetworkUseCase {
    suspend fun<T> baseResponse(apiCall:suspend ()-> Response<T>): ApiResponse<T> {
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