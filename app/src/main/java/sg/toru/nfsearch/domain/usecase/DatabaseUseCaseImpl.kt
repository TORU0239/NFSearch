package sg.toru.nfsearch.domain.usecase

import sg.toru.nfsearch.data.database.NFSearchDatabase
import sg.toru.nfsearch.data.entity.SearchResult
import javax.inject.Inject

class DatabaseUseCaseImpl @Inject constructor(private val database:NFSearchDatabase):DatabaseUseCase {
    override fun query(query: String): List<SearchResult> {
        return database.searchDao().getSearch(query)
    }
}