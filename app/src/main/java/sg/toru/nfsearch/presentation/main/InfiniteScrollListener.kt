package sg.toru.nfsearch.presentation.main

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class InfiniteScrollListener(
    private val layoutManager: GridLayoutManager,
    private val onLoadMoreListener: OnLoadMoreListener
):RecyclerView.OnScrollListener() {

    private var isLoading:Boolean = false
    private val visibleThreadHold = 6

    private var totalItemCount:Int = 0
    private var lastVisibleItem:Int = 0

    fun getLoaded() = isLoading
    fun setLoaded() {
        isLoading = false
    }
    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        if (dy <= 0) return

        totalItemCount = layoutManager.itemCount
        lastVisibleItem = layoutManager.findLastVisibleItemPosition()

        if(!isLoading && totalItemCount <= lastVisibleItem + visibleThreadHold) {
            onLoadMoreListener.onLoadMore()
            isLoading = true
        }
    }
}

interface OnLoadMoreListener {
    fun onLoadMore()
}