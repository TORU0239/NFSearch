package sg.toru.nfsearch.domain.usecaseimp

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import sg.toru.nfsearch.data.api.NetworkUtil
import sg.toru.nfsearch.domain.usecase.ImageSearchUseCase

class ImageSearchUseCaseImpl: ImageSearchUseCase {
    override fun request() {
        CoroutineScope(Dispatchers.IO).launch {
            NetworkUtil.service().getImageSearch(query("ladygaga", "1"))
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