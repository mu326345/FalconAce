package com.yuyu.falconace.news

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yuyu.falconace.R
import com.yuyu.falconace.data.News
import com.yuyu.falconace.data.NewsItem
import com.yuyu.falconace.data.NewsItem.Content
import com.yuyu.falconace.data.source.INewsRepository
import com.yuyu.falconace.network.LoadApiStatus
import com.yuyu.falconace.util.Util.getString
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class NewsViewModel(private val repository: INewsRepository) : ViewModel() {

    private val _newsContentResult = MutableLiveData<News>()
    val newsContentResult: LiveData<News>
        get() = _newsContentResult

    private val _newsItemResult = MutableLiveData<List<NewsItem>>()
    val newsItemResult: LiveData<List<NewsItem>>
        get() = _newsItemResult

    private val _status = MutableLiveData<LoadApiStatus>()
    val status: LiveData<LoadApiStatus>
        get() = _status

    private val _error = MutableLiveData<String>()
    val error: LiveData<String>
        get() = _error

    // Create a Coroutine scope using a job to be able to cancel when needed
    private var viewModelJob = Job()

    // the Coroutine runs using the Main (UI) dispatcher
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    init {
        getNewsContentResult()
    }

    private fun getNewsContentResult(isInitial: Boolean = false) {

        coroutineScope.launch {

            if (isInitial) _status.value = LoadApiStatus.LOADING

            val result = repository.getNewsResult()

            val newsResult = when (result) {
                is com.yuyu.falconace.data.Result.Success -> {
                    _error.value = null
                    if (isInitial) _status.value = LoadApiStatus.DONE
                    result.data
                }
                is com.yuyu.falconace.data.Result.Fail -> {
                    _error.value = result.error
                    if (isInitial) _status.value = LoadApiStatus.ERROR
                    null
                }
                is com.yuyu.falconace.data.Result.Error -> {
                    _error.value = result.exception.toString()
                    if (isInitial) _status.value = LoadApiStatus.ERROR
                    null
                }
                else -> {
                    _error.value = getString(R.string.something_wrong)
                    if (isInitial) _status.value = LoadApiStatus.ERROR
                    null
                }
            }
            toNewsItem(newsResult)
        }
    }

    fun toNewsItem(result: News?) {
        val newsItem = mutableListOf<NewsItem>()

        var tempSection = ""
        result?.getVector?.items?.forEachIndexed { pos, item ->
            if (item.type == "news" &&
                item._meta?.section != null) {

                if (!tempSection.isEmpty()) {
                    if (tempSection == item._meta.section) {

                        if (item.appearance?.mainTitle != null &&
                            item.appearance.thumbnail?.substring(
                                item.appearance.thumbnail.length - 3) == "jpg") {
                            newsItem.add(Content(item))
                        }

                    } else {
                        newsItem.add(NewsItem.SectionTitle(tempSection))
                        tempSection = item._meta.section
                    }
                } else {
                    tempSection = item._meta.section
                }
            }

            if (result.getVector.items.size -1 == pos) {
                newsItem.add(NewsItem.SectionTitle(tempSection))
            }
        }

        var tempIndex = 0
        val newNewsList = mutableListOf<NewsItem>()
        newsItem.forEachIndexed { index, newItem ->

            if (newItem is NewsItem.SectionTitle) {
                newNewsList.add(tempIndex, NewsItem.SectionTitle(newItem.title))
                tempIndex = index + 1
            } else if (newItem is Content) {
                newNewsList.add(Content(newItem.newsItem))
            }
        }

        _newsItemResult.value = newNewsList
    }
}