package com.yuyu.falconace.data.source.remote

import android.content.Context
import com.yuyu.falconace.R
import com.yuyu.falconace.data.News
import com.yuyu.falconace.data.Result
import com.yuyu.falconace.data.source.INewsRepository
import com.yuyu.falconace.data.source.NewsRepository
import com.yuyu.falconace.network.NewsApi
import com.yuyu.falconace.util.Logger


class NewsRemoteDataSource(val context: Context) : INewsRepository {

    override suspend fun getNewsResult(): com.yuyu.falconace.data.Result<News> {

        return try {
            // this will run on a thread managed by Retrofit
            val newsResult = NewsApi.retrofitService.getNewsResult()

            com.yuyu.falconace.data.Result.Success(newsResult)
        } catch (e: Exception) {
            Logger.w("[${this::class.simpleName}] exception=${e.message}")
            Result.Error(e)
        }
    }
}