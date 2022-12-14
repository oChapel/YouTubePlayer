package com.och.musicplayer.ui.adapter.holders

import android.view.View
import com.och.musicplayer.data.dto.SearchItem
import com.och.musicplayer.databinding.ItemSearchResultBinding
import com.och.musicplayer.ui.adapter.ClickEvent
import com.squareup.picasso.Picasso
import kotlinx.coroutines.flow.MutableSharedFlow

class SearchResultItemHolder(
    private val binding: ItemSearchResultBinding,
    private val clickFlow: MutableSharedFlow<ClickEvent>
) : YouTubeItemViewHolder<SearchItem>(binding.root), View.OnClickListener {

    private var searchItem: SearchItem? = null

    init {
        binding.root.setOnClickListener(this)
    }

    override fun bind(item: SearchItem) {
        this.searchItem = item
        Picasso.get().load(item.snippet.thumbnails.medium.url).into(binding.itemSearchResultCover)
        binding.itemSearchResultTitle.text = item.snippet.title
        binding.itemSearchResultChannel.text = item.snippet.channelTitle
    }

    override fun onClick(view: View?) {
        searchItem?.let { if (view == binding.root) clickFlow.tryEmit(ClickEvent.OnItemClicked(it)) }
    }
}
