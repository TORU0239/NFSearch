package sg.toru.nfsearch.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import sg.toru.nfsearch.data.entity.SearchResult

@Dao
interface SearchDao {

    //'%'||upper('입력받은 조건')||'%'

    @Query("SELECT * FROM SearchResult WHERE UPPER(title) LIKE '%'||UPPER(:query)||'%'")
    fun getSearch(query:String):List<SearchResult>

    @Query("SELECT COUNT(*) FROM SearchResult WHERE LOWER(title) LIKE LOWER(:query)")
    fun getTotalImageForQuery(query: String):Int

    @Query("SELECT COUNT(*) FROM SearchResult")
    fun getTotalNumber():Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSearchResult(resultList:List<SearchResult>)
}