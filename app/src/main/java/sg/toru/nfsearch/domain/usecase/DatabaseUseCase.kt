package sg.toru.nfsearch.domain.usecase

import sg.toru.nfsearch.data.entity.SearchResult

interface DatabaseUseCase {
    fun query(query:String):List<SearchResult>
    fun save(list:List<SearchResult>)
    fun getTotalCount():Int
}