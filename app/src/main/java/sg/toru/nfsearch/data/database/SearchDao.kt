package sg.toru.nfsearch.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import sg.toru.nfsearch.data.entity.SearchResult

@Dao
interface SearchDao {

    @Query("SELECT * FROM SearchResult WHERE title IN (:query)")
    fun getSearch(query:String):List<SearchResult>

    @Query("SELECT COUNT(*) FROM SearchResult WHERE title IN (:query)")
    fun getTotalImageForQuery(query: String):Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSearchResult(resultList:List<SearchResult>)
}