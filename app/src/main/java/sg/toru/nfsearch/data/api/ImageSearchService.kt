package sg.toru.nfsearch.data.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap
import sg.toru.nfsearch.data.entity.SearchResultWrapper

interface ImageSearchService {
    @GET(NetworkUtil.IMAGESEARCHURL)
    suspend fun getImageSearch(@QueryMap map:Map<String,String>): Response<SearchResultWrapper>
}