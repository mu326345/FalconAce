package com.yuyu.falconace.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.yuyu.falconace.data.source.INewsRepository
import com.yuyu.falconace.news.NewsViewModel

class ViewModelFactory(val repository: INewsRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        with(modelClass) {
            when {
                isAssignableFrom(NewsViewModel::class.java) ->
                    NewsViewModel(repository)

                else ->
                    throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
            }
        } as T
}