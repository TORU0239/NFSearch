package sg.toru.nfsearch.presentation.binder

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso
import sg.toru.nfsearch.presentation.glide.GlideApp

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