package sg.toru.nfsearch.domain.usecaseimp

import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import sg.toru.nfsearch.data.api.ImageSearchService
import sg.toru.nfsearch.domain.usecase.ImageSearchUseCase
import javax.inject.Inject


class ImageSearchUseCaseImpl @Inject constructor(private val service: ImageSearchService): ImageSearchUseCase {
    override fun request(query:String) {
        CoroutineScope(Dispatchers.IO).launch {
            val result = service.getImageSearch(query(query, "1"))
            withContext(Dispatchers.Main) {
                Log.e("ImageSearchUseCaseImpl", "size:: ${result.value.size}")
            }
        }
    }

    private fun query(
        query:String,
        pageNumber:String
    ):HashMap<String, String> {
        val map = HashMap<String,String>()
        map["autoCorrect"] = "true"
        map["safeSearch"] = "false"
        map["q"] = query
        map["pageNumber"] = pageNumber
        return map
    }

}