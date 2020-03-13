package sg.toru.nfsearch.presentation.binder

import android.graphics.drawable.Drawable
import android.util.Log
import android.view.View
import android.webkit.WebView
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import sg.toru.nfsearch.R
import sg.toru.nfsearch.data.api.NetworkUtil.URL_FOR_SEARCH
import sg.toru.nfsearch.data.entity.SearchResult
import sg.toru.nfsearch.presentation.adapter.MainSearchAdapter
import sg.toru.nfsearch.presentation.glide.GlideApp

@BindingAdapter("loadImage")
fun ImageView.loadImage(url:String) {
    GlideApp.with(this)
        .load(url)
        .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
        .skipMemoryCache(false)
        .placeholder(R.drawable.placeholder)
        .error(R.drawable.placeholder)
        .into(this)
}

@BindingAdapter("loadImageUrl", "width", "height", requireAll = true)
fun ImageView.loadImageWithDimension(
    url:String,
    width:Int,
    height:Int
) {
    val view = this

//    val myHeight =if (width > height) {
//        ((layoutParams.width.toFloat()) * (height.toFloat() / width.toFloat())).toInt()
//
//
//    } else {
//        ((layoutParams.width.toFloat()) * (width.toFloat() / height.toFloat())).toInt()
//    }

    val myHeight = ((layoutParams.width.toFloat()) * (height.toFloat() / width.toFloat())).toInt()

    val params = view.layoutParams
    params.height = myHeight
    view.layoutParams = params
    view.invalidate()

    GlideApp.with(this)
        .load(url)
        .override(layoutParams.width, myHeight)
        .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
        .skipMemoryCache(false).listener(object:RequestListener<Drawable>{
            override fun onLoadFailed(
                e: GlideException?,
                model: Any?,
                target: Target<Drawable>?,
                isFirstResource: Boolean
            ): Boolean {
                view.layoutParams.height = myHeight
                view.setImageResource(R.drawable.placeholder)
                e?.printStackTrace()
                return true
            }

            override fun onResourceReady(
                resource: Drawable?,
                model: Any?,
                target: Target<Drawable>?,
                dataSource: DataSource?,
                isFirstResource: Boolean
            ): Boolean = false
        })
        .placeholder(R.drawable.placeholder)
        .into(this)
}


@BindingAdapter("addItems", "clearCurrentList", requireAll = true)
fun RecyclerView.addItems(
    itemLists:List<SearchResult>?,
    isClearCurrentList:Boolean?
) {
    itemLists?.let{
        isClearCurrentList?.let { clear ->
            if(clear) {
                ((this.adapter) as MainSearchAdapter).clearList()
            }
        }
        ((this.adapter) as MainSearchAdapter).updateList(itemLists as ArrayList<SearchResult>)
    }
}

@BindingAdapter("currentLoadingStatus")
fun ProgressBar.currentLoadingStatus(status:Boolean) {
    this.visibility = if (status) View.VISIBLE else View.GONE
}

@BindingAdapter("toast")
fun View.toast(str:String?) {
    str?.let {
        val error = String.format(context.getString(R.string.error_message), it)
        Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
    }
}

@BindingAdapter("loadingUrl")
fun WebView.loadingUrl(url:String?) {
    url?.let {
        this.loadUrl("$URL_FOR_SEARCH$it")
    }
}