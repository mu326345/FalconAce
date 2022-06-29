package com.yuyu.falconace.data.source

import com.yuyu.falconace.data.News
import com.yuyu.falconace.data.Result
import com.yuyu.falconace.data.source.remote.NewsRemoteDataSource

class NewsRepository(private val dataSource: NewsRemoteDataSource) : INewsRepository {

    override suspend fun getNewsResult(): Result<News> {
        return dataSource.getNewsResult()
    }
}