package sg.toru.nfsearch.presentation.adapter

import androidx.recyclerview.widget.RecyclerView
import sg.toru.nfsearch.data.entity.SearchResult
import sg.toru.nfsearch.databinding.ItemMainSearchBinding

class SearchViewHolder(
    private val binding: ItemMainSearchBinding,
    private val click:(SearchResult)->Unit
): RecyclerView.ViewHolder(binding.root) {
    fun bindItem(result: SearchResult) {
        binding.item = result
        binding.executePendingBindings()
        binding.root.setOnClickListener {
            click.invoke(result)
        }
    }
}