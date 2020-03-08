package sg.toru.nfsearch.data.entity

data class SearchResult (
    val url:String,
    val height:Int,
    val width:Int,
    val thumbnail:String,
    val thumbnailHeight:String,
    val thumbnailWidth:String,
    val base64Encoding:String,
    val name:String,
    val title:String,
    val imageWebSearchUrl:String
)