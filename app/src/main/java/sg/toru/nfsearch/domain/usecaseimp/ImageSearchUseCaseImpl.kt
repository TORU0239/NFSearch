package sg.toru.nfsearch.domain.usecaseimp

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import sg.toru.nfsearch.data.api.ImageSearchService
import sg.toru.nfsearch.domain.usecase.ImageSearchUseCase
import javax.inject.Inject


class ImageSearchUseCaseImpl @Inject constructor(private val service: ImageSearchService): ImageSearchUseCase {
    override fun request() {
        CoroutineScope(Dispatchers.IO).launch {
            service.getImageSearch(query("ladygaga", "1"))
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