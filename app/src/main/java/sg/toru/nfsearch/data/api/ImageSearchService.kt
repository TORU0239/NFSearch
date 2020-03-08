package sg.toru.nfsearch.data.api

import retrofit2.http.GET
import retrofit2.http.QueryMap
import sg.toru.nfsearch.data.entity.SearchResult

interface ImageSearchService {
    /*
    * ?autoCorrect=true&pageNumber=4&pageSize=20&q=ladygaga&safeSearch=false"
    * */

    @GET(NetworkUtil.IMAGESEARCHURL)
    suspend fun getImageSearch(@QueryMap map:Map<String,String>):List<SearchResult>

    fun query(
        query:String,
        pageNumber:String
    ):HashMap<String, String> {
        val map = HashMap<String,String>()
        map["q"] = query
        map["pageNumber"] = pageNumber
        return map
    }
}