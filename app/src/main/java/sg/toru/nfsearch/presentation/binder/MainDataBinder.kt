package sg.toru.nfsearch.presentation.binder

import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso
import sg.toru.nfsearch.data.entity.SearchResult
import sg.toru.nfsearch.presentation.glide.GlideApp
import sg.toru.nfsearch.presentation.main.MainSearchAdapter

@BindingAdapter("loadImage")
fun ImageView.loadImage(url:String) {
//    GlideApp.with(this)
//        .load(url)
//        .diskCacheStrategy(DiskCacheStrategy.NONE)
//        .skipMemoryCache(true)
//        .into(this)
    Picasso.get().load(url)
        .memoryPolicy(MemoryPolicy.NO_CACHE)
        .networkPolicy(NetworkPolicy.NO_CACHE)
        .into(this)
}

@BindingAdapter("addItems")
fun RecyclerView.addItems(itemLists:List<SearchResult>?) {
    itemLists?.let{
        Log.e("Toru", "MainDataBinder success size:: ${it.size}")
        ((this.adapter) as MainSearchAdapter).updateList(itemLists as ArrayList<SearchResult>)
    }
}