package com.yuyu.falconace.data

sealed class NewsItem {

    data class SectionTitle(val title: String) : NewsItem()

    data class Content(val newsItem: Item): NewsItem()
}