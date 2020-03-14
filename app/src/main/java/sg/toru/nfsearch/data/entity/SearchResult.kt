package sg.toru.nfsearch.data.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity
@Parcelize
data class SearchResult (
    val url:String,
    val height:Int,
    val width:Int,
    val thumbnail:String,
    val thumbnailHeight:String,
    val thumbnailWidth:String,
    val base64Encoding:String?,
    val name:String,
    @PrimaryKey
    val title:String,
    val imageWebSearchUrl:String
):Parcelable

/*
*
* {3 items
"_type":"images"
"totalCount":4417
"value":[...]20 items
}*/
data class SearchResultWrapper(
    val type:String,
    val totalCount:String,
    val value:List<SearchResult>
)