package sg.toru.nfsearch.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import sg.toru.nfsearch.data.entity.SearchResult
import sg.toru.nfsearch.databinding.ItemMainSearchBinding

class MainSearchListAdapter(
    itemCallback: MainItemCallback,
    private val onClick:(SearchResult)->Unit
): ListAdapter<SearchResult, SearchViewHolder>(itemCallback){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val binding = ItemMainSearchBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchViewHolder(binding){
            onClick.invoke(it)
        }
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bindItem(getItem(position))
    }

    fun clearList() {
        submitList(listOf())
    }
}

class MainItemCallback : DiffUtil.ItemCallback<SearchResult>(){
    override fun areItemsTheSame(oldItem: SearchResult, newItem: SearchResult): Boolean = (oldItem.title == newItem.title)
    override fun areContentsTheSame(oldItem: SearchResult, newItem: SearchResult): Boolean = (oldItem.title == newItem.title)
}
