package com.yuyu.falconace.news

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.yuyu.falconace.data.Item
import com.yuyu.falconace.data.News
import com.yuyu.falconace.data.NewsItem
import com.yuyu.falconace.databinding.ItemNewsContentBinding
import com.yuyu.falconace.databinding.ItemNewsSectionTitleBinding

class NewsAdapter : ListAdapter<NewsItem, RecyclerView.ViewHolder>(DiffCallback) {

    companion object DiffCallback : DiffUtil.ItemCallback<NewsItem>() {
        override fun areItemsTheSame(oldItem: NewsItem, newItem: NewsItem): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: NewsItem, newItem: NewsItem): Boolean {
            return oldItem == newItem
        }

        private const val ITEM_SECTION_TITLE = 0x00
        private const val ITEM_CONTENT = 0x01
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {

            is NewsItem.SectionTitle -> ITEM_SECTION_TITLE
            else -> ITEM_CONTENT
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType) {

            ITEM_SECTION_TITLE -> SectionTitleViewHolder(
                ItemNewsSectionTitleBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )

            ITEM_CONTENT -> ContentViewHolder (
                ItemNewsContentBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )

            else -> throw ClassCastException("Unknown viewType $viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {

            is SectionTitleViewHolder -> {
                holder.bind(getItem(position) as NewsItem.SectionTitle)
            }
            is ContentViewHolder -> {
                holder.bind(getItem(position) as NewsItem.Content)
            }
        }
    }

    class SectionTitleViewHolder(private var binding: ItemNewsSectionTitleBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(title: NewsItem.SectionTitle) {

            binding.title = title
            binding.executePendingBindings()
        }
    }

    class ContentViewHolder(private var binding: ItemNewsContentBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(newsItem: NewsItem.Content) {

            binding.item = newsItem
            binding.executePendingBindings()
        }
    }
}