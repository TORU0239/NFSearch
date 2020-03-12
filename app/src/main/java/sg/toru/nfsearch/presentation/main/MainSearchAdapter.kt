package sg.toru.nfsearch.presentation.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import okhttp3.internal.notify
import sg.toru.nfsearch.data.entity.SearchResult
import sg.toru.nfsearch.databinding.ItemMainSearchBinding
import java.util.*

class MainSearchAdapter(): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var recyclerviewListItem: ArrayList<SearchResult> = ArrayList()
        set(value) {
            val currentSize = field.size
            field = value
            notifyDataSetChanged()
        }

    fun clearList() {
        recyclerviewListItem.clear()
    }

    fun updateList(resultList:ArrayList<SearchResult>) {
        if(recyclerviewListItem.isEmpty()) {
            recyclerviewListItem = resultList
            notifyDataSetChanged()
        } else {
            val prevSize = recyclerviewListItem.size
            recyclerviewListItem.addAll(resultList)
            notifyItemInserted(prevSize)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ItemMainSearchBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as SearchViewHolder).bindItem(recyclerviewListItem[position])
    }

    override fun getItemCount(): Int = recyclerviewListItem.size
}

class SearchViewHolder(private val binding: ItemMainSearchBinding): RecyclerView.ViewHolder(binding.root) {
    fun bindItem(result:SearchResult) {
        binding.item = result
        binding.executePendingBindings()
    }
}

class LoadingViewHolder(view: View): RecyclerView.ViewHolder(view)